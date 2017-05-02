package com.developer.paul.recyclerview;

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
                return new LinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    public static LayoutManagerFactory linearHorizontal(){
        return new LayoutManagerFactory() {
            @Override
            public LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false);
            }
        };
    }
}
