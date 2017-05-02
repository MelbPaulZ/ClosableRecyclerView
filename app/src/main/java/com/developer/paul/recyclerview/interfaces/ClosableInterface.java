package com.developer.paul.recyclerview.interfaces;

import android.databinding.ObservableList;

import java.util.HashMap;

/**
 * Created by Paul on 2/5/17.
 */

public interface ClosableInterface<T> {
    void deleteItem(int position);
    void addItem(int position, T t);
    void setOrderDictionary(HashMap<String, Integer> orderDictionary);
    void setObservableList(ObservableList<T> observableList);
}
