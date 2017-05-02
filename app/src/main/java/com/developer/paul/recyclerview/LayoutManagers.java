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
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    public static LayoutManagerFactory linear(){
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext());
            }
        };
    }
}
