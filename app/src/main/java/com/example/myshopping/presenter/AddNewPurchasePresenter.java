package com.example.myshopping.presenter;

import android.content.Context;

import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.repository.Repository;

public class AddNewPurchasePresenter {

    private Repository repository;

    public AddNewPurchasePresenter(Context context) {
        repository = new Repository(context);
    }

    public void insertPurchase(Purchase purchase) {
        repository.insertPurchase(purchase);
    }

    public void insertAddingToHistory(HistoryItem item) {
        repository.insertHistoryItem(item);
    }

    public void dispose() {
        repository.dispose();
    }
}
