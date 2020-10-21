package com.btxdev.multastransito.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.btxdev.multastransito.Multa;

public class MultaDBController {

    private SQLiteSimpleDatabase db;

    public MultaDBController(Context c){
        this.db = new SQLiteSimpleDatabase(c, TableMulta.create_query, 1);
    }

    public Multa add(Multa multa){
        try{
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TableMulta.col_placa, multa.getPlaca());
            values.put(TableMulta.col_modelo, multa.getPlaca());
            values.put(TableMulta.col_direccion_inf, multa.getPlaca());
            values.put(TableMulta.col_tipo_comparendo, multa.getPlaca());
            values.put(TableMulta.col_cedula_inf, multa.getPlaca());
            long id = sql.insert(TableMulta.table_name, null, values);
            multa.setId(id);
            return multa;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return null;
        }
    }

    public boolean edit(Multa multa){
        try{
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TableMulta.col_placa, multa.getPlaca());
            values.put(TableMulta.col_modelo, multa.getPlaca());
            values.put(TableMulta.col_direccion_inf, multa.getPlaca());
            values.put(TableMulta.col_tipo_comparendo, multa.getPlaca());
            values.put(TableMulta.col_cedula_inf, multa.getPlaca());
            sql.update(TableMulta.table_name, values, TableMulta.col_id+"=?",new String[]{Long.toString(multa.getId())});
            return true;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return false;
        }
    }

    public boolean remove(String id){
        try{
            SQLiteDatabase sql = db.getWritableDatabase();
            sql.delete(TableMulta.table_name, TableMulta.col_id+"=?",new String[]{id});
            return true;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return false;
        }
    }

    public Cursor getListCursor(){
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor cursor = sql.query(TableMulta.table_name, null, null,
                null, null, null, null, null);
        return cursor;
    }


}
