package com.developer.paul.recyclerview.viewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.developer.paul.recyclerview.BR;
import com.developer.paul.recyclerview.interfaces.ClosableInterface;
import com.developer.paul.recyclerview.interfaces.Positionable;

/**
 * Created by Paul on 2/5/17.
 */

public class ItemViewModel extends BaseObservable implements Positionable{
    private String name;
    private ClosableInterface closableInterface;
    private int position;
    private String type;

    public ItemViewModel(String type, String name, int position) {
        this.type = type;
        this.name = name;
        this.position = position;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClosableInterface getClosableInterface() {
        return closableInterface;
    }

    public void setClosableInterface(ClosableInterface closableInterface) {
        this.closableInterface = closableInterface;
    }

    public View.OnClickListener onDeleteClick(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (closableInterface !=null){
                    closableInterface.deleteItem(position);
                }
            }
        };
    }


    public View.OnClickListener onItemClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click Item " + type, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
