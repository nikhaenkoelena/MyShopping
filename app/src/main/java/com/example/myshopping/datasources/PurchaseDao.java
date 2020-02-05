package com.example.myshopping.datasources;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myshopping.repository.Purchase;

import java.util.List;

@Dao
public abstract class PurchaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertPurchases(List<Purchase> purchases);

    @Query("DELETE FROM purchases_table")
    public abstract void deleteAllPurchases();

    @Query("SELECT * FROM purchases_table")
    public abstract List<Purchase> getAllPurchases();
}
