package com.example.meri.task.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.meri.task.R;

public class CustomView extends View {

    private static final int FIRST_PHOTO = 1;

    private static final int SECOND_PHOTO = 2;

    private static final int THIRD_PHOTO = 3;

    private int mSelectedImageNumber;

    private float mRotateDegree;
    private float mScaleFactor = 1f;

    private ScaleGestureDetector mScaleDetector;

    private Rect mRect;


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void setNumber(int number) {
        mSelectedImageNumber = number;
    }

    private void init(Context context) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleGestureListener());

        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mSelectedImageNumber != 0) {
            int width = getWidth() / 2;
            int height = getHeight() / 2;

            Bitmap selectedBitmap = getSelectedAsBitmap();

            if (selectedBitmap != null) {
                Bitmap bitmap = getResizedBitmap(selectedBitmap, 500);

                int left = width - bitmap.getWidth() / 2;
                int top = height - bitmap.getHeight() / 2;
                int right = width + bitmap.getWidth() / 2;
                int bottom = height + bitmap.getHeight() / 2;

                mRect.set(left, top, right, bottom);

                if (mRotateDegree != 0f) {
                    canvas.rotate(mRotateDegree, width, height);
                }

                canvas.scale(mScaleFactor, mScaleFactor, width, height);
                canvas.drawBitmap(bitmap, null, mRect, null);
            }
        }
    }

    private Bitmap getSelectedAsBitmap() {
        Bitmap bitmap = null;

        if (mSelectedImageNumber == FIRST_PHOTO) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_image);
        } else if (mSelectedImageNumber == SECOND_PHOTO) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.second_image);
        } else if (mSelectedImageNumber == THIRD_PHOTO) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.third_image);
        }

        return bitmap;
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int maxSize) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width > height && width <= maxSize || width < height && height <= maxSize) {
            return bitmap;
        } else {
            float bitmapRatio = (float) width / (float) height;
            if (bitmapRatio > 1) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }

            return Bitmap.createScaledBitmap(bitmap, width, height, true);
        }
    }

    public void rotateView(float degree) {
        mRotateDegree = degree;

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);

        return true;
    }

    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(1f, Math.min(mScaleFactor, 20.0f));

            invalidate();

            return true;
        }
    }
}