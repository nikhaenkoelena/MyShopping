package com.example.myshopping.datasources.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myshopping.datasources.PurchaseDao;
import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Purchase;

@Database(entities = {Purchase.class, HistoryItem.class}, version = 3, exportSchema = false)
public abstract class PurchaseDatabase extends RoomDatabase {

    private static PurchaseDatabase database;
    private static final String DB_NAME = "purchdatabase.db";
    private static final Object LOCK = new Object();

    public static PurchaseDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, PurchaseDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
            return database;
        }
    }

    public abstract PurchaseDao purchaseDao();
}
