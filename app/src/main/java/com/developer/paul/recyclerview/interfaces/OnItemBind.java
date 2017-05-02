package com.developer.paul.recyclerview.interfaces;

import com.developer.paul.recyclerview.ItemBinding;

/**
 * Created by Paul on 1/5/17.
 */

public interface OnItemBind<T> {
    /**
     * Called on each item in the collection, allowing you to modify the given {@link ItemBinding}.
     * Note that you should not do complex processing in this method as it's called many times.
     */
    void onItemBind(ItemBinding itemBinding, int position, T item);
}
