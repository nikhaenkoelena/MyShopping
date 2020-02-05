package com.example.myshopping.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myshopping.R;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.ui.adapter.PurchaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ToBuyFragment extends Fragment {

    @BindView(R.id.recyclerViewPurchasesList) RecyclerView recyclerView;
    @BindView(R.id.textViewToBuyLabel) TextView textViewToBuy;
    @BindView(R.id.textViewBoughtLabel) TextView textViewBought;
    private PurchaseAdapter adapter;

    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_buy, container, false);
        unbinder = ButterKnife.bind(this, view);
        Purchase purchase = new Purchase(1, "Olalala", "12.15", true);
        Purchase purchase2 = new Purchase(1, "Ohahaha", "12.20", false);
        List<Purchase> purch = new ArrayList<>();
        purch.add(purchase);
        purch.add(purchase2);
        adapter = new PurchaseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setPurch(purch);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
