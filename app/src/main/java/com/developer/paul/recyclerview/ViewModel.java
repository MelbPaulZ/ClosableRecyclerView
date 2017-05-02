package com.developer.paul.recyclerview;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.developer.paul.recyclerview.interfaces.ClosableInterface;
import com.developer.paul.recyclerview.itemDecorators.BindingRecyclerViewAdapter;
import com.developer.paul.recyclerview.itemDecorators.DividerItemDecorator;

import java.util.HashMap;

/**
 * Created by Paul on 2/5/17.
 */

public class ViewModel implements ClosableInterface<ItemViewModel> {

    public ObservableList<ItemViewModel> itemViewModelObservableList = new ObservableArrayList<>();

    public HashMap<String, Integer> orderDictionary;


    public void setOrderDictionary(HashMap<String, Integer> orderDictionary){
        this.orderDictionary = orderDictionary;
    }

    public void setItemViewModelObservableList(ObservableList<ItemViewModel> itemViewModelObservableList) {
        this.itemViewModelObservableList = itemViewModelObservableList;
        setHideAndShowInterfaceForList(this.itemViewModelObservableList, this);
    }

    private void setHideAndShowInterfaceForList(ObservableList<ItemViewModel> itemViewModels, ClosableInterface hideAndShowInterface){
        for (ItemViewModel itemViewModel: itemViewModels){
            itemViewModel.setClosableInterface(hideAndShowInterface);
        }
    }

    private void setHideAndShowInterfaceForSingleViewModel(ItemViewModel itemViewModel, ClosableInterface hideAndShowInterface){
        itemViewModel.setClosableInterface(hideAndShowInterface);
    }

    public BindingRecyclerViewAdapter adapter = new BindingRecyclerViewAdapter<>();

    public final ItemBinding<ItemViewModel> singleItem = ItemBinding.of(BR.item, R.layout.item);

    public DividerItemDecorator divider = new DividerItemDecorator(Color.BLACK, 1f);


    @Override
    public void deleteItem(int position) {
        adapter.removeItem(position);
        updateListPositions();
    }

    @Override
    public void addItem(int position, ItemViewModel itemViewModel) {
        adapter.addItem(position, itemViewModel);
        updateListPositions();
    }



    private void updateListPositions(){
        int pos = 0;
        for(ItemViewModel vm : itemViewModelObservableList){
            vm.setPosition(pos);
            pos++;
        }
    }

    public View.OnClickListener addItem(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((Button)v).getText().toString();
                for(int i = 0 ; i < itemViewModelObservableList.size(); i++){
                    int clickedNum = orderDictionary.get(name);
                    int itemNumber = orderDictionary.get(itemViewModelObservableList.get(i).getType());
                    if (itemNumber >= clickedNum){
                        // find the right place to put in
                        addAndSetInterfaceForViewModel(name, name, i,ViewModel.this);
                        return;
                    }
                }
                // if current name is the largest one
                addAndSetInterfaceForViewModel(name, name, itemViewModelObservableList.size(),ViewModel.this);
            }
        };
    }

    private void addAndSetInterfaceForViewModel(String type, String name, int position,ClosableInterface closableInterface){
        ItemViewModel newItemViewModel = new ItemViewModel(type, name,position);
        setHideAndShowInterfaceForSingleViewModel(newItemViewModel, closableInterface);
        addItem(position, newItemViewModel);
    }

    private static class MyAwesomeViewHolder extends RecyclerView.ViewHolder {
        public MyAwesomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * Custom view holders for RecyclerView
     */
    public final BindingRecyclerViewAdapter.ViewHolderFactory viewHolder = new BindingRecyclerViewAdapter.ViewHolderFactory() {
        @Override
        public RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding) {
            return new MyAwesomeViewHolder(binding.getRoot());
        }
    };



}
