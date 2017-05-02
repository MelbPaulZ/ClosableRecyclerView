package com.developer.paul.recyclerview.viewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import com.developer.paul.recyclerview.BR;
import com.developer.paul.recyclerview.interfaces.Positionable;

/**
 * Created by Paul on 2/5/17.
 */

public class IconItemViewModel extends BaseObservable implements Positionable{

    private String name;
    private int position;

    public IconItemViewModel(String name, int position) {
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
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}
