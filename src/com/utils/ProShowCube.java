package com.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.saarang.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class ProShowCube {
	private FloatBuffer vtxBuff;
	private FloatBuffer txtrBuff;
	private int nFaces = 6;

	public float[] normalX = new float[nFaces];
	public float[] normalY = new float[nFaces];

	// Please ensure the images are of same dimensions and the last two are rotated 90 degree
	// Or just give equal sized squares
	private int[] imgIDs = {
			R.drawable.gallery_photo_1,
			R.drawable.gallery_photo_2, 
			R.drawable.gallery_photo_3, 
			R.drawable.gallery_photo_4, 
			R.drawable.gallery_photo_5,
			R.drawable.gallery_photo_6
	};

	private int[] txtrIDs = new int[nFaces];
	private Bitmap[] bitmap = new Bitmap[nFaces];
	private float cubeHalfSize = 1.0f;

	public ProShowCube(Context context) {
		ByteBuffer vtxBB = ByteBuffer.allocateDirect(12*4*nFaces);
		vtxBB.order(ByteOrder.nativeOrder());
		vtxBuff = vtxBB.asFloatBuffer();

		for (int face = 0; face < nFaces; face++) {
			bitmap[face] = BitmapFactory.decodeStream(context.getResources().openRawResource(imgIDs[face]));
			int imgWidth = bitmap[face].getWidth();
			int imgHeight = bitmap[face].getHeight();
			float faceWidth = 2.0f;
			float faceHeight = 2.0f;

			if (imgWidth > imgHeight) {
				faceHeight = faceHeight*imgHeight/imgWidth;
			} else {
				faceWidth = faceWidth*imgWidth/imgHeight;
			}
			float faceLeft = -faceWidth/2;
			float faceRight = -faceLeft;
			float faceTop = faceHeight/2;
			float faceBottom = -faceTop;

			// Note that gl can't handle squares directly. Use two triangles.
			// Order is 012, 213
			float[] vtxs = {
					faceLeft, faceBottom, 0.0f, 	//0
					faceRight, faceBottom, 0.0f,	//1
					faceLeft, faceTop, 0.0f,		//2
					faceRight, faceTop, 0.0f,		//3
			};
			vtxBuff.put(vtxs);
			Log.e("ProShowCube", " " + face + " " + faceLeft + " " + faceRight + " " + faceLeft + " " + faceTop + " " + faceBottom);
		}
		vtxBuff.position(0);

		//		normalX[face] = faceLeft + faceRight 

		float[] txtrCoords = {
				0.0f, 1.0f,	// 0 left-bottom
				1.0f, 1.0f,	// 1 right-bottom
				0.0f, 0.0f,	// 2 left-top
				1.0f, 0.0f	// 3 right-top
		};
		ByteBuffer txtrBB = ByteBuffer.allocateDirect(txtrCoords.length*4*nFaces);
		txtrBB.order(ByteOrder.nativeOrder());
		txtrBuff = txtrBB.asFloatBuffer();
		for (int face = 0; face < nFaces; face++) {
			txtrBuff.put(txtrCoords);
		}
		txtrBuff.position(0);
	}

	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vtxBuff);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, txtrBuff);

		// front
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, cubeHalfSize);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[0]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glPopMatrix();

		// left
		gl.glPushMatrix();
		gl.glRotatef(270.0f, 0f, 1f, 0f);
		gl.glTranslatef(0f, 0f, cubeHalfSize);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[1]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
		gl.glPopMatrix();

		// back
		gl.glPushMatrix();
		gl.glRotatef(180.0f, 0f, 1f, 0f);
		gl.glTranslatef(0f, 0f, cubeHalfSize);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[2]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		gl.glPopMatrix();

		// right
		gl.glPushMatrix();
		gl.glRotatef(90.0f, 0f, 1f, 0f);
		gl.glTranslatef(0f, 0f, cubeHalfSize);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
		gl.glPopMatrix();

		// top
		gl.glPushMatrix();
		gl.glRotatef(270.0f, 1f, 0f, 0f);
		gl.glTranslatef(0f, 0f, cubeHalfSize*0.75f);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[4]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		gl.glPopMatrix();

		// bottom
		gl.glPushMatrix();
		gl.glRotatef(90.0f, 1f, 0f, 0f);
		gl.glTranslatef(0f, 0f, cubeHalfSize*0.75f);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[5]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
		gl.glPopMatrix();

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);	
	}

	public void loadTexture(GL10 gl) {
		gl.glGenTextures(6, txtrIDs, 0);

		for (int face = 0; face < nFaces; face++) {
			gl.glBindTexture(GL10.GL_TEXTURE_2D, txtrIDs[face]);
			//	         gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
			//	         gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

			// Build Texture from loaded bitmap for the currently-bind texture ID
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap[face], 0);
			bitmap[face].recycle();
		}
	}
}
