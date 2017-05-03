package com.developer.paul.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

/**
 * Created by Paul on 2/5/17.
 */

public class LayoutManagers {
    public LayoutManagers(){

    }

    public interface LayoutManagerFactory{
        LayoutManager create(RecyclerView recyclerView);
    }

    public static LayoutManagerFactory linear(){
        return new LayoutManagerFactory() {
            @Override
            public LayoutManager create(RecyclerView recyclerView) {
                return new NonScrollLinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    public static LayoutManagerFactory linearHorizontal(){
        return new LayoutManagerFactory() {
            @Override
            public LayoutManager create(RecyclerView recyclerView) {
                return new NonScrollLinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false);
            }
        };
    }

    private static class NonScrollLinearLayoutManager extends LinearLayoutManager{


        public NonScrollLinearLayoutManager(Context context) {
            super(context);
        }

        public NonScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }

        @Override
        public boolean canScrollHorizontally() {
            return false;
        }
    }
}
