package com.example.myshopping.presenter;

import android.content.Context;

import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.repository.Repository;
import com.example.myshopping.ui.fragments.ToBuyInterface;

import java.util.ArrayList;
import java.util.List;

public class ToBuyPresenter {

    private Repository repository;
    private List<Purchase> purchases;
    private ToBuyInterface toBuyInterface;

    public ToBuyPresenter(Context context, ToBuyInterface toBuyInterface) {
        this.toBuyInterface = toBuyInterface;
        repository = new Repository(context);
        purchases = new ArrayList<>();

    }

    public void getAllPurchases(Boolean isBought) {
        repository.getAllPurchases(this, isBought);
    }

    public void setAllPurchases(List<Purchase> purchases) {
        toBuyInterface.setPurchases(purchases);
    }

    public void updatePurchaseState(int id, boolean isChecked) {
        repository.updatePurchaseState(id, isChecked);
    }

    public void deletePurchase(int id) {
        repository.deletePurchase(id);
    }

    public void insertHistoryItem(HistoryItem item) {
        repository.insertHistoryItem(item);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void dispose() {
        repository.dispose();
    }
}
