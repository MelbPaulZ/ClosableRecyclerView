package com.developer.paul.recyclerview.interfaces;

/**
 * Created by Paul on 2/5/17.
 */

public interface ClosableInterface<T> {
    void deleteItem(int position);
    void addItem(int position, T t);
}
