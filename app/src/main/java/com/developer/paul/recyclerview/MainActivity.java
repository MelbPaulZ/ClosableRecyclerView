package com.developer.paul.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.developer.paul.recyclerview.itemDecorators.DividerItemDecorator;
import com.developer.paul.recyclerview.itemDecorators.StartOffsetItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentRecyclerView fragmentRecyclerView = new FragmentRecyclerView();
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout_container, fragmentRecyclerView).commit();
    }
}
