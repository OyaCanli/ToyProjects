package com.canlioya.customviewexercise;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyCustomView extends View {

    Paint mPaint;
    private Rect mRect;
    int mSquareColor;
    int mPadding = 0;

    public MyCustomView(Context context) {
        super(context);
        init(null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();

        if (set == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.MyCustomView);
        mSquareColor = typedArray.getColor(R.styleable.MyCustomView_square_color, Color.GREEN);
        mPaint.setColor(mSquareColor);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.left = mPadding;
        mRect.right = getWidth()- mPadding;
        mRect.top = mPadding;
        mRect.bottom = getHeight() - mPadding;

        canvas.drawRect(mRect, mPaint);
    }

    public void swapColor(){
        mPaint.setColor(mPaint.getColor() == mSquareColor ? Color.RED : mSquareColor);
        postInvalidate();
        requestLayout();
    }

    public void customPaddingUp(int padding){
        mPadding += padding;
        postInvalidate();
        requestLayout();
    }

    public void customPaddingDown(int padding){
        mPadding -= padding;
        postInvalidate();
        requestLayout();
    }
}
