package com.developer.paul.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.paul.recyclerview.databinding.FragmentRecyclerviewBinding;

import java.util.HashMap;

/**
 * Created by Paul on 2/5/17.
 */

public class FragmentRecyclerView extends Fragment {
    private ViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        viewModel = new ViewModel();
        HashMap<String, Integer> orderHashMap = new HashMap<>();
        orderHashMap.put("Location", 0);
        orderHashMap.put("Repeat",1);
        orderHashMap.put("Note",2);
        viewModel.setOrderDictionary(orderHashMap);

        ObservableList<ItemViewModel> itemViewModelObservableList = new ObservableArrayList<>();
        itemViewModelObservableList.add(new ItemViewModel("Location", "Location", 0));
        itemViewModelObservableList.add(new ItemViewModel("Repeat", "Repeat", 1));
        itemViewModelObservableList.add(new ItemViewModel("Note", "Note", 2));
        viewModel.setItemViewModelObservableList(itemViewModelObservableList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRecyclerviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview, container,false);
        binding.setVm(viewModel);
        return binding.getRoot();

    }
}
