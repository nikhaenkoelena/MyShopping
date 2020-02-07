package com.example.myshopping.repository;

import android.content.Context;

import com.example.myshopping.datasources.database.PurchaseDatabase;
import com.example.myshopping.presenter.ToBuyPresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private static PurchaseDatabase database;
    private CompositeDisposable compositeDisposable;


    public Repository(Context context) {
        database = PurchaseDatabase.getInstance(context);
        compositeDisposable = new CompositeDisposable();
    }


    public void getAllPurchases(final ToBuyPresenter presenter, Boolean isBought) {
        compositeDisposable.add(database.purchaseDao().getAllPurchases(isBought)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Purchase>>() {
                    @Override
                    public void accept(List<Purchase> purchases) throws Exception {
                        presenter.setAllPurchases(purchases);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }));
    }

    public void insertPurchase(final Purchase purchase) {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (purchase != null) {
                    database.purchaseDao().insertPurchase(purchase);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void updatePurchaseState (final int id, final boolean isChecked) {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.purchaseDao().updatePurchaseState(id, isChecked);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void updatePurchase (final int id, final boolean isChecked) {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.purchaseDao().updatePurchase(id, isChecked);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void deletePurchase (int id) {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.purchaseDao().deletePurchase(id);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
