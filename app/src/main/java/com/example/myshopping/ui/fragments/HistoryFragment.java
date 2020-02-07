package com.example.myshopping.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myshopping.R;
import com.example.myshopping.presenter.HistoryPresenter;
import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.ui.adapter.HistoryAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends Fragment implements HistoryInterface {

    @BindView(R.id.recyclerViewHistory)
    RecyclerView recyclerView;

    private HistoryAdapter adapter;
    private HistoryPresenter presenter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        Objects.requireNonNull(getActivity()).setTitle(R.string.app_title);
        unbinder = ButterKnife.bind(this, view);
        adapter = new HistoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        presenter = new HistoryPresenter(getContext(), this);
        presenter.getHistoryItems();
        return view;
    }

    @Override
    public void setHistoryItems(List<HistoryItem> items) {
        Comparator<HistoryItem> comparator = Comparator
                .comparing(HistoryItem::getTime).reversed();
        Collections.sort(items, comparator);
        adapter.setHistorys(items);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.dispose();
    }


}
