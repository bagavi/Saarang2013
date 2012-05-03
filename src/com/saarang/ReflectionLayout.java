/*
 *
 Vivek has no fundeas the document this
 */
package com.saarang;
import com.saarang.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Generic layout which creates a glass reflection look. Measuring has many
 * side-effects and works best with a single ImageView child but should be
 * flexible enough to display any other type of view.
 */

public class ReflectionLayout extends FrameLayout {
    private static final String TAG = "ReflectionLayout";

    private static final boolean DEBUG_DRAWING_TIME = false;

    /**
     * Desired reflection layout size (including the child). This should be
     * configurable at runtime somehow. It may be smaller than this depending on
     * layout constraints.
     */
    private static final float REFLECTION_SIZE = 1.20f;

    /* Drawing tools used to create the reflection pool effect. */
    private final Paint mBorderPaint = new Paint();
    private final Paint mShadowPaint = new Paint();



    public ReflectionLayout(Context context) {
        this(context, null);
    }

    public ReflectionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReflectionLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setWillNotDraw(false);

        mBorderPaint.setColor(Color.BLACK);
        mBorderPaint.setStyle(Style.STROKE);
        mBorderPaint.setStrokeWidth((float)2.0);
        mBorderPaint.setAntiAlias(true);
        mShadowPaint.setShadowLayer((float)3.0,(float) 3.0, (float)3.0, Color.BLACK);
        mShadowPaint.setAntiAlias(true);
        mShadowPaint.setColor(Color.TRANSPARENT);

    }

    @Override
    protected void onMeasure(int wspec, int hspec) {
        super.onMeasure(wspec, hspec);

        if (getChildCount() > 0) {
            View child = getChildAt(0);
            int childw = child.getMeasuredWidth();
            int childh = child.getMeasuredHeight();
            setMeasuredDimension(resolveSize(childw+3, wspec),
                    resolveSize((int)(childh+3), hspec));
        }
    }

    
    @Override
    protected void onDraw(Canvas canvas) {
    	/*
		 * Question
		 * Whats the purpose here ?
		 */
    	long now;
        if (DEBUG_DRAWING_TIME) {
            now = System.currentTimeMillis();
        }

        
        if (getChildCount() > 0) {
        	Log.d(TAG,"reached here");
            drawShadow(canvas);
        }

        if (DEBUG_DRAWING_TIME) {
            long elapsed = System.currentTimeMillis() - now;
            Log.d(TAG, "Drawing took " + elapsed + " ms");
        }
    }

    private void drawShadow(Canvas canvas) {
        View child = getChildAt(0);
        int childw = child.getWidth();
        int childh = child.getHeight();
        child.draw(canvas);
        super.onDraw(canvas);
        canvas.drawRect(0, 0, childw,childh, mBorderPaint);
        canvas.drawRect(0, 0, childw, childh, mShadowPaint);

    }
}
