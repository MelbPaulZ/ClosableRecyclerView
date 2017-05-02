package com.developer.paul.recyclerview.itemDecorators;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.developer.paul.recyclerview.ItemBinding;
import com.developer.paul.recyclerview.interfaces.BindingCollectionAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Paul on 1/5/17.
 */

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BindingCollectionAdapter<T>{
    private final Object DATA_INVALIDATION = new Object();
    private ItemBinding<T> itemBinding;
    private List<T> items;
    private LayoutInflater inflater;
    private ItemIds<? super T> itemIds;
    private ViewHolderFactory viewHolderFactory;
    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);
    @Nullable
    private RecyclerView recyclerView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = onCreateBinding(inflater, viewType, parent);
        final RecyclerView.ViewHolder holder = onCreateViewHolder(binding);
//        binding.addOnRebindCallback(new OnRebindCallback() {
//            @Override
//            public boolean onPreBind(ViewDataBinding binding) {
//                return recyclerView != null && recyclerView.isComputingLayout();
//            }
//
//            @Override
//            public void onCanceled(ViewDataBinding binding) {
//                if (recyclerView == null || recyclerView.isComputingLayout()) {
//                    return;
//                }
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    notifyItemChanged(position, DATA_INVALIDATION);
//                }
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item = items.get(position);
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        onBindBinding(binding, itemBinding.variableId(), itemBinding.layoutRes(), position, item);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void setItemBinding(ItemBinding<T> itemBinding) {
        this.itemBinding = itemBinding;
    }

    @Override
    public int getItemViewType(int position) {
        itemBinding.onItemBind(position, items.get(position));
        return itemBinding.layoutRes();
    }

    @Override
    public ItemBinding<T> getItemBinding() {
        return this.itemBinding;
    }

    @Override
    public void setItems(@Nullable List<T> items) {
        if(this.items == items){
            return;
        }

        if (recyclerView!=null){
            if (this.items instanceof ObservableList){
                ((ObservableList<T>) this.items).removeOnListChangedCallback(callback);
            }
            if (items instanceof ObservableList){
                ((ObservableList<T>)items).addOnListChangedCallback(callback);
            }
        }
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public T getAdapterItem(int position) {
        return items.get(position);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutRes, ViewGroup viewGroup) {
        return DataBindingUtil.inflate(inflater, layoutRes, viewGroup, false);
    }

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, @LayoutRes int layoutRes, int position, T item) {
        if (itemBinding.bind(binding, item)){
            binding.executePendingBindings();
        }
    }

    public ViewHolder onCreateViewHolder(ViewDataBinding binding) {
        if (viewHolderFactory != null) {
            return viewHolderFactory.createViewHolder(binding);
        } else {
            return new BindingViewHolder(binding);
        }
    }

    public void setItemIds(ItemIds<? super T> itemIds) {
        this.itemIds = itemIds;
    }

    public void setViewHolderFactory(ViewHolderFactory viewHolderFactory) {
        this.viewHolderFactory = viewHolderFactory;
    }


    public void removeItem(int position){
        this.items.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int position, T t){
        this.items.add(position, t);
        notifyItemInserted(position);
    }

    private static class BindingViewHolder extends ViewHolder {
        public BindingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingRecyclerViewAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> adapter) {
            this.adapterRef = new WeakReference<>(adapter);
        }

        @Override
        public void onChanged(ObservableList sender) {
            BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, final int positionStart, final int itemCount) {
            BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, final int positionStart, final int itemCount) {
            BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, final int fromPosition, final int toPosition, final int itemCount) {
            BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            for (int i = 0; i < itemCount; i++) {
                adapter.notifyItemMoved(fromPosition + i, toPosition + i);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, final int positionStart, final int itemCount) {
            BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    public interface ItemIds<T>{
        long getItemId(int position, T item);
    }

    public interface ViewHolderFactory{
        RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding);
    }
}
