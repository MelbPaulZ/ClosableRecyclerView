package com.developer.paul.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;

import com.developer.paul.recyclerview.interfaces.OnItemBind;

/**
 * Created by Paul on 2/5/17.
 */

public class OnItemBindClass<T> implements OnItemBind<T> {

    private final SimpleArrayMap<Class<? extends T>, int[]> itemBindingMap;

    public OnItemBindClass() {
        this.itemBindingMap = new SimpleArrayMap<>();
    }

    /**
     * Maps the given class to the given variableId and layout. This is an exact match, no
     * inheritance it taken into account.
     */
    public OnItemBindClass<T> map(@NonNull Class<? extends T> itemClass, int variableId, @LayoutRes int layoutRes) {
        itemBindingMap.put(itemClass, new int[]{variableId, layoutRes});
        return this;
    }


    public int itemTypeCount() {
        return itemBindingMap.size();
    }

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, T item) {
        for (int i = 0; i < itemBindingMap.size(); i++) {
            Class<? extends T> key = itemBindingMap.keyAt(i);
            if (key.isInstance(item)) {
                int[] values = itemBindingMap.valueAt(i);
                itemBinding.set(values[0], values[1]);
                return;
            }
        }
        throw new IllegalArgumentException("Missing class for item " + item);
    }
}
