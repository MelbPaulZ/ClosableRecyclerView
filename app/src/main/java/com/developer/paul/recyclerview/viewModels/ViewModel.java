package com.developer.paul.recyclerview.viewModels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.developer.paul.recyclerview.BR;
import com.developer.paul.recyclerview.BindingRecyclerViewAdapter;
import com.developer.paul.recyclerview.ItemBinding;
import com.developer.paul.recyclerview.R;
import com.developer.paul.recyclerview.interfaces.ClosableInterface;
import com.developer.paul.recyclerview.itemDecorators.DividerItemDecorator;

import java.util.HashMap;

/**
 * Created by Paul on 2/5/17.
 */

public class ViewModel extends BaseViewModel<ItemViewModel>{

    public DividerItemDecorator divider = new DividerItemDecorator(Color.BLACK, 1f);

    @Override
    public void setObservableList(ObservableList<ItemViewModel> observableList) {
        super.setObservableList(observableList);
        setHideAndShowInterfaceForList(this.observableList, this);
    }

    private void setHideAndShowInterfaceForList(ObservableList<ItemViewModel> itemViewModels,
                                                ClosableInterface hideAndShowInterface){
        for (ItemViewModel itemViewModel: itemViewModels){
            itemViewModel.setClosableInterface(hideAndShowInterface);
        }
    }

    private void setHideAndShowInterfaceForSingleViewModel(ItemViewModel itemViewModel,
                                                           ClosableInterface hideAndShowInterface){
        itemViewModel.setClosableInterface(hideAndShowInterface);
    }

    public final ItemBinding<ItemViewModel> singleItem = ItemBinding.of(BR.item, R.layout.item);


    public View.OnClickListener addItem(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((Button)v).getText().toString();
                for(int i = 0 ; i < observableList.size(); i++){
                    int clickedNum = orderDictionary.get(name);
                    int itemNumber = orderDictionary.get(observableList.get(i).getType());
                    if (itemNumber >= clickedNum){
                        // find the right place to put in
                        addAndSetInterfaceForViewModel(name, name, i,ViewModel.this);
                        return;
                    }
                }
                // if current name is the largest one
                addAndSetInterfaceForViewModel(name, name, observableList.size(),ViewModel.this);
            }
        };
    }

    private void addAndSetInterfaceForViewModel(String type,
                                                String name,
                                                int position,
                                                ClosableInterface closableInterface){
        ItemViewModel newItemViewModel = new ItemViewModel(type, name,position);
        setHideAndShowInterfaceForSingleViewModel(newItemViewModel, closableInterface);
        addItem(position, newItemViewModel);
    }
}
