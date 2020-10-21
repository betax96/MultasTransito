package com.btxdev.multastransito.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteSimpleDatabase extends SQLiteOpenHelper {

    private String createTableQuery;


    public SQLiteSimpleDatabase(@Nullable Context context, String createTableQuery, int version) {
        super(context, TableMulta.nameDb, null, version);
        this.createTableQuery = createTableQuery;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

}
