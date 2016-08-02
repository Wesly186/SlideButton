package com.wesly186.slidebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Wesly186 on 2016/8/2.
 */
public class SlideButton extends View {

    private Bitmap slide;
    private Bitmap slideBackground;
    private float currentX = 0;
    private boolean isSliding;
    private boolean toggleState;
    private onToggleStateChangeListener listener;


    public SlideButton(Context context) {
        super(context);
    }

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSlideBackgroundResource(int switchBackground) {
        slideBackground = BitmapFactory.decodeResource(getResources(), switchBackground);
    }

    public void setSlideResource(int slideImage) {
        slide = BitmapFactory.decodeResource(getResources(), slideImage);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(slideBackground.getWidth(), slideBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(slideBackground, 0, 0, null);

        float slideX = currentX - slide.getWidth() / 2;
        if (slideX < 0) {
            slideX = 0;
        } else if (slideX > slideBackground.getWidth() - slide.getWidth()) {
            slideX = slideBackground.getWidth() - slide.getWidth();
        }

        if (isSliding) {
            canvas.drawBitmap(slide, slideX, 0, null);
        } else {
            if (toggleState) {
                canvas.drawBitmap(slide, slideBackground.getWidth() - slide.getWidth(), 0, null);
            } else {
                canvas.drawBitmap(slide, 0, 0, null);
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSliding = true;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isSliding = false;
                if (currentX < slideBackground.getWidth() / 2) {
                    if(toggleState !=false){
                        toggleState = false;
                        listener.onToggleStateChange(false);
                    }
                } else {
                    if(toggleState !=true){
                        toggleState = true;
                        listener.onToggleStateChange(true);
                    }
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setToggleState(boolean toggleState) {
        this.toggleState = toggleState;
        invalidate();
    }

    public void setOnToggleStateChangeListener(onToggleStateChangeListener listener) {
        this.listener = listener;
    }

    public interface onToggleStateChangeListener {
        void onToggleStateChange(boolean toogleOn);
    }
}
