
package com.saarang;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.utils.ProShowCube;
import com.utils.MatrixStack;

/**
 * This, wait for it, is the ProShowActivity.
 * Expect a cube to rotate at your touch.
 * See ProShowCube.java
 * 
 * To understand this code, get javadoc for gl:
 * 	1. download bindings_gles-1_0_1-mrel-spec.zip from http://jcp.org/aboutJava/communityprocess/mrel/jsr239/index.html
 * 	2. Replace <android>/docs/reference/javax/microedition/khronos/egl/EGL10.html
 * 	3. Replace <android>/docs/reference/javax/microedition/khronos/opengles/GL10.html
 * 	4. Restart eclipse :)
 */
public class ProShowActivity extends Activity {
	private GLSurfaceView mGLSurfaceView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create our Preview view and set it as the content of our
		// Activity
		mGLSurfaceView = new TouchSurfaceView(this);
		//		mGLSurfaceView.setGLWrapper(new GLSurfaceView.GLWrapper() {
		//			public GL wrap(GL gl) {
		//				return new MatrixTrackingGL(gl);
		//			}
		//		});
		setContentView(mGLSurfaceView);
		mGLSurfaceView.requestFocus();
		mGLSurfaceView.setFocusableInTouchMode(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLSurfaceView.onPause();
	}
}

/**
 * Implement a simple rotation control.
 */
class TouchSurfaceView extends GLSurfaceView {
	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private final float TRACKBALL_SCALE_FACTOR = 36.0f;
	private ProShowCubeRenderer mRenderer;
	private float mPreviousX;
	private float mPreviousY;

	public TouchSurfaceView(Context context) {
		super(context);
		mRenderer = new ProShowCubeRenderer(context);
		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	@Override public boolean onTrackballEvent(MotionEvent e) {
		mRenderer.mAngleX += e.getX() * TRACKBALL_SCALE_FACTOR;
		mRenderer.mAngleY += e.getY() * TRACKBALL_SCALE_FACTOR;
		requestRender();
		return true;
	}

	@Override public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		switch (e.getAction()) {
		case MotionEvent.ACTION_MOVE:
			float dx = x - mPreviousX;
			float dy = y - mPreviousY;
			mRenderer.mAngleX += dx * TOUCH_SCALE_FACTOR;
			mRenderer.mAngleY += dy * TOUCH_SCALE_FACTOR;
			requestRender();
		}
		mPreviousX = x;
		mPreviousY = y;
		return true;
	}


	private class ProShowCubeRenderer implements GLSurfaceView.Renderer {
		private ProShowCube leCube;
		//    	private float angle = 0;
		//    	private float defaultSpeed = -1f;
		public float mAngleX = -10.0f;
		public float mAngleY = 10.0f;
		//        private int[] mProjectionMatrix = new int[16];
		//		public int[] viewPort = new int[4];

		private float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
		private float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
		private float[] lightPosition = {0.0f, 0.0f, 2.0f, 1.0f};
		
		public ProShowCubeRenderer(Context context) {
			leCube = new ProShowCube(context);
			Log.e("ProShow", "leCube");
		}

		public void onDrawFrame(GL10 gl) {
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			
			gl.glEnable(GL10.GL_LIGHTING);
			
			parseAngles();

			gl.glLoadIdentity();
			//			gl.glTranslatef(0.0f, -2.0f, -6.0f);
			gl.glTranslatef(0.0f, 0.0f, -0.5f);
			gl.glRotatef(mAngleX, 0, 1, 0);
			gl.glRotatef(mAngleY, 1, 0, 0);
			gl.glScalef(0.65f, 0.65f, 0.65f);
			gl.glEnable(GL10.GL_BLEND);
			gl.glDisable(GL10.GL_DEPTH_TEST);
			leCube.draw(gl);

			Log.e("ProShow", "le Cube finished drawing");
//			Log.e("ProShow", "" + mAngleX + " " + mAngleY);
			
			//			MatrixGrabber matrixGrabber = new MatrixGrabber();
			//			gl.glPushMatrix();
			//			matrixGrabber.getCurrentState(gl);
			//			Log.e("ProShow", " " + matrixGrabber.mModelView[0] + " " + matrixGrabber.mModelView[1] + " " + matrixGrabber.mModelView[2] + " " 
			//					+ matrixGrabber.mModelView[3] + " " + matrixGrabber.mModelView[4] + " " + matrixGrabber.mModelView[5] + " " + matrixGrabber.mModelView[6] 
			//							+ " " + matrixGrabber.mModelView[7] + " " + matrixGrabber.mModelView[8] + " " + matrixGrabber.mModelView[9]);
			//			float[] temp = new float[4];
			//			float[] tmp = new float[4];
			//			GLU.gluUnProject((float) viewPort[2]/2.0f, (float) viewPort[3]/2.0f, 1.0f, matrixGrabber.mModelView, 0, matrixGrabber.mProjection, 0, viewPort, 0, tmp, 0);
			//			Log.e("ProShow", ""+tmp[0] + " " + tmp[1] + " " + tmp[2] + " " + tmp[3]);
			//			Matrix.multiplyMV(temp, 0, matrixGrabber.mModelView, 0, tmp, 0);
			//			Log.e("ProShow", "Near " + temp[0] / temp[3] + " " + temp[1] / temp[3] + " " + temp[2] / temp[3]);
			//			gl.glPopMatrix();
		}

		private void parseAngles() {
			if (mAngleX > 225)
				mAngleX -= 360;
			else if (mAngleX < -225)
				mAngleX += 360;
			if (mAngleY > 225)
				mAngleY -= 360;
			else if (mAngleY < -225)
				mAngleY += 360;

			if (mAngleX > 45 && mAngleX < 135)
				Log.e("ProShow", "Proshow 1");
			else if (mAngleX < -45 && mAngleX > -135)
				Log.e("ProShow", "Proshow 3");
			else if ((Math.abs(mAngleX) < 45 && Math.abs(mAngleY) < 45) || (Math.abs(mAngleX) > 135 && Math.abs(mAngleY) > 135))
				Log.e("ProShow", "Proshow 0");
			else if ( (Math.abs(mAngleX) < 45 && Math.abs(mAngleY) > 135) || (Math.abs(mAngleX) > 135 && Math.abs(mAngleY) < 45))
				Log.e("ProShow", "Proshow 2");
			else if ((Math.abs(mAngleX) < 45 && mAngleY > 45 && mAngleY < 135) || Math.abs(mAngleX) > 135 && mAngleY < -45 && mAngleY > -135)
				Log.e("ProShow", "Proshow 4");
			else if ((Math.abs(mAngleX) < 45 && mAngleY < -45 && mAngleY > -135) || Math.abs(mAngleX) > 135 && mAngleY > 45 && mAngleY < 135)
				Log.e("ProShow", "Proshow 5");
		}

		public void onSurfaceChanged(GL10 gl, int width, int height) {
			gl.glViewport(0, 0, width, height);
			Log.e("ProShow", "surface changed");
			//			viewPort[0] = 0;
			//			viewPort[1] = 0;
			//			viewPort[2] = width;
			//			viewPort[3] = height;
			//			gl.glPushMatrix();
			//			gl.glGetIntegerv(GL11.GL_MODELVIEW_MATRIX_FLOAT_AS_INT_BITS_OES, mProjectionMatrix, 0);
			//			Log.e("ProShow", " " + mProjectionMatrix[0]);
			//			gl.glPopMatrix();
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			gl.glClearDepthf(1.0f);
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glDepthFunc(GL10.GL_LEQUAL);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glDisable(GL10.GL_DITHER);
			gl.glEnable(GL10.GL_CULL_FACE);
			gl.glEnable(GL10.GL_TEXTURE_2D);
			leCube.loadTexture(gl);
			

			//			gl.glColor4f(1.0f, 1.0f, 1.0f, 0.95f);
			//			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			
			gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, lightAmbient, 0);
			gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, lightDiffuse, 0);
			gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, lightPosition, 0);
			gl.glEnable(GL10.GL_LIGHT1);
			gl.glEnable(GL10.GL_LIGHT0);
			
			Log.e("ProShow", "Surface created with texture and lighting");
		}
	}
}


