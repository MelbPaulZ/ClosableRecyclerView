package com.developer.paul.recyclerview.itemDecorators;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Paul on 22/4/17.
 */

public class StartOffsetItemDecoration extends RecyclerView.ItemDecoration {
    private int startOffset;

    public StartOffsetItemDecoration(int startOffset) {
        this.startOffset = startOffset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildPosition(view) == 0){
            outRect.top = startOffset;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
