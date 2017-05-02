package com.developer.paul.recyclerview.itemDecorators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Paul on 22/4/17.
 */

public class DividerItemDecorator extends RecyclerView.ItemDecoration {
    private final Paint mPaint;
    private final int mAlpha;

    public DividerItemDecorator(int color, float width){
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setStrokeWidth(width);
        mAlpha = mPaint.getAlpha();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

        final int position = params.getViewPosition();

        if (position < state.getItemCount()){
            outRect.set(0, 0, 0, (int)mPaint.getStrokeWidth());
        }else{
            outRect.setEmpty();
        }
    }



    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int offset = (int) (mPaint.getStrokeWidth()/2);

        for(int i = 0 ; i < parent.getChildCount() ; i ++){
            final View view = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

            final int position = params.getViewPosition();

            if (position < state.getItemCount()){
                mPaint.setAlpha((int) view.getAlpha() * mAlpha);

                float positionY = view.getBottom() + offset + view.getTranslationY();

                c.drawLine(view.getLeft() + view.getTranslationX(),
                        positionY,
                        view.getRight() + view.getTranslationX(),
                        positionY,
                        mPaint);
            }
        }

    }
}
