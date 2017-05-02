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
import com.developer.paul.recyclerview.viewModels.IconItemViewModel;
import com.developer.paul.recyclerview.viewModels.IconViewModel;
import com.developer.paul.recyclerview.viewModels.ItemViewModel;
import com.developer.paul.recyclerview.viewModels.ViewModel;

import java.util.HashMap;

/**
 * Created by Paul on 2/5/17.
 */

public class FragmentRecyclerView extends Fragment {
    private ViewModel viewModel;
    private IconViewModel iconViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HashMap<String, Integer> orderHashMap = new HashMap<>();
        orderHashMap.put("Location", 0);
        orderHashMap.put("Repeat",1);
        orderHashMap.put("Note",2);
        viewModel = new ViewModel();
        iconViewModel = new IconViewModel();
        viewModel.setOrderDictionary(orderHashMap);
        iconViewModel.setOrderDictionary(orderHashMap);

        ObservableList<ItemViewModel> itemViewModelObservableList = new ObservableArrayList<>();
        itemViewModelObservableList.add(new ItemViewModel("Location", "Location", 0));
        itemViewModelObservableList.add(new ItemViewModel("Repeat", "Repeat", 1));
        itemViewModelObservableList.add(new ItemViewModel("Note", "Note", 2));
        viewModel.setObservableList(itemViewModelObservableList);

        ObservableList<IconItemViewModel> iconItemViewModelObservableList = new ObservableArrayList<>();
        iconItemViewModelObservableList.add(new IconItemViewModel("Location", 0));
        iconItemViewModelObservableList.add(new IconItemViewModel("Repeat", 1));
        iconItemViewModelObservableList.add(new IconItemViewModel("Note", 2));
        iconViewModel.setObservableList(iconItemViewModelObservableList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRecyclerviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview, container,false);
        binding.setVm(viewModel);
        binding.setIconVM(iconViewModel);
        return binding.getRoot();

    }
}
