package com.example.myshopping.presenter;

import android.content.Context;

import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Repository;
import com.example.myshopping.ui.fragments.HistoryInterface;

import java.util.ArrayList;
import java.util.List;

public class HistoryPresenter {

    private Repository repository;
    private List<HistoryItem> items;
    private HistoryInterface historyInterface;

    public HistoryPresenter(Context context, HistoryInterface historyInterface) {
        this.historyInterface = historyInterface;
        repository = new Repository(context);
        items = new ArrayList<>();
    }

    public void getHistoryItems() {
        repository.getHistoryItems(this);
    }

    public void setHistoryItems(List<HistoryItem> historyItems) {
        historyInterface.setHistoryItems(historyItems);
    }

    public void deleteHist() {
        repository.deleteHist();
    }

    public void dispose() {
        repository.dispose();
    }
}
