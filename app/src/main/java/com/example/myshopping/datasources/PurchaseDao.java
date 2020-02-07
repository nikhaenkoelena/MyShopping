package com.example.myshopping.datasources;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.myshopping.repository.Purchase;

import java.util.List;

import io.reactivex.Observable;

@Dao
public abstract class PurchaseDao {

    @Transaction
    public void updatePurchaseState (int id, boolean isBought) {
        updatePurchase(id, isBought);
        getAllPurchases(!isBought);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertPurchase(Purchase purchase);

    @Query("DELETE FROM purchases_table WHERE uniqId == :id")
    public abstract void deletePurchase(int id);

    @Query("SELECT * FROM purchases_table WHERE isBought == :isBought")
    public abstract Observable<List<Purchase>> getAllPurchases(boolean isBought);

    @Query("UPDATE purchases_table SET isBought = :isBought WHERE uniqId == :id ")
    public abstract void updatePurchase(int id, boolean isBought);

}
