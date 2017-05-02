package com.developer.paul.recyclerview.viewModels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developer.paul.recyclerview.BindingRecyclerViewAdapter;
import com.developer.paul.recyclerview.interfaces.ClosableInterface;
import com.developer.paul.recyclerview.interfaces.Positionable;
import com.developer.paul.recyclerview.itemDecorators.DividerItemDecorator;

import java.util.HashMap;

/**
 * Created by Paul on 2/5/17.
 */

public class BaseViewModel<T extends Positionable> implements ClosableInterface<T>{

    public ObservableList<T> observableList = new ObservableArrayList<>();
    public BindingRecyclerViewAdapter adapter = new BindingRecyclerViewAdapter<>();


    /**
     * Custom view holders for RecyclerView
     */
    public final BindingRecyclerViewAdapter.ViewHolderFactory viewHolder = new BindingRecyclerViewAdapter.ViewHolderFactory() {
        @Override
        public RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding) {
            return new MyAwesomeViewHolder(binding.getRoot());
        }
    };


    public HashMap<String, Integer> orderDictionary;

    @Override
    public void deleteItem(int position) {
        adapter.removeItem(position);
        updateListPositions();
    }

    @Override
    public void addItem(int position, T t) {
        adapter.addItem(position, t);
        updateListPositions();
    }

    private void updateListPositions(){
        int pos = 0;
        for(T vm : observableList){
            vm.setPosition(pos);
            pos++;
        }
    }

    @Override
    public void setObservableList(ObservableList<T> observableList) {
        this.observableList = observableList;
    }

    @Override
    public void setOrderDictionary(HashMap<String, Integer> orderDictionary) {
        this.orderDictionary = orderDictionary;
    }

    protected static class MyAwesomeViewHolder extends RecyclerView.ViewHolder {
        public MyAwesomeViewHolder(View itemView) {
            super(itemView);
        }
    }

}
