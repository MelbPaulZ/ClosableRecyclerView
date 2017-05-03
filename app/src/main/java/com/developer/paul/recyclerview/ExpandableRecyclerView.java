package com.developer.paul.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Paul on 3/5/17.
 */

public class ExpandableRecyclerView extends RecyclerView {
    private int width, height;
    private String TAG = "ExpandableRecyclerView";

    public ExpandableRecyclerView(Context context) {
        super(context);
    }

    public ExpandableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        width = MeasureSpec.getSize(widthSpec);
        height = MeasureSpec.getSize(heightSpec);
        Log.i(TAG, "onMeasure: " + width + ", " + height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i(TAG, "onLayout: " + getChildCount());
        int childCount = getChildCount();
        int dir = ((LinearLayoutManager)getLayoutManager()).getOrientation();

        Log.i(TAG, "onLayout: " + dir);

        for (int i = 0 ; i < childCount ; i ++){
            View v = getChildAt(i);
            ViewGroup.LayoutParams lp = v.getLayoutParams();
            if (dir == RecyclerView.HORIZONTAL) {
                int left = i * width / childCount;
                int top = 0;
                int right = (i + 1) * width / childCount;
                int bottom = height;
                lp.height = bottom - top;
                lp.width = right - left;
            }else{
                int left = 0;
                int top = i * height / childCount;
                int right = width;
                int bottom = (i + 1) * height / childCount;
                lp.height = bottom - top;
                lp.width = right - left;
            }
        }
    }
}
