package com.example.myshopping.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myshopping.R;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.ui.adapter.PurchaseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ToBuyFragment extends Fragment {

    @BindView(R.id.recyclerViewPurchasesList) RecyclerView recyclerView;
    @BindView(R.id.textViewToBuyLabel) TextView textViewToBuy;
    @BindView(R.id.textViewBoughtLabel) TextView textViewBought;
    @BindView(R.id.buttonAddNewPurchaseMain) FloatingActionButton button;
    private PurchaseAdapter adapter;
    private NavController navController;

    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_buy, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new PurchaseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        onClickAddNewPurchase();
    }

    private void onClickAddNewPurchase () {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_toBuyFragment_to_addNewPurchaseFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
