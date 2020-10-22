package com.btxdev.multastransito.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.btxdev.multastransito.Multa;

import java.util.ArrayList;
import java.util.List;

public class MultaDBController {

    private SQLiteSimpleDatabase db;
    public static final String db_name = "multa_db";

    public MultaDBController(Context context){
        db = new SQLiteSimpleDatabase(context, db_name, TableMulta.create_query, 1);
    }

    public Multa add(Multa multa){
        try{
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TableMulta.col_placa, multa.getPlaca());
            values.put(TableMulta.col_modelo, multa.getModelo());
            values.put(TableMulta.col_direccion_inf, multa.getDireccionInfraccion());
            values.put(TableMulta.col_tipo_comparendo, multa.getTipoComparendo());
            values.put(TableMulta.col_cedula_inf, multa.getCedulaInfractor());
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
            values.put(TableMulta.col_modelo, multa.getModelo());
            values.put(TableMulta.col_direccion_inf, multa.getDireccionInfraccion());
            values.put(TableMulta.col_tipo_comparendo, multa.getTipoComparendo());
            values.put(TableMulta.col_cedula_inf, multa.getCedulaInfractor());
            sql.update(TableMulta.table_name, values, TableMulta.col_id+"=?",new String[]{Long.toString(multa.getId())});
            return true;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return false;
        }
    }

    public boolean remove(long id){
        try{
            SQLiteDatabase sql = db.getWritableDatabase();
            sql.delete(TableMulta.table_name, TableMulta.col_id+"=?",new String[]{Long.toString(id)});
            return true;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return false;
        }
    }

    public List<Multa> list(){
        try{
            SQLiteDatabase sql = db.getReadableDatabase();
            Cursor cursor = sql.query(TableMulta.table_name, null, null,
                    null, null, null, null, null);
            List<Multa> multas = new ArrayList<>();
            while(cursor.moveToNext()){
                long id = cursor.getLong(0);
                String placa = cursor.getString(1);
                String modelo = cursor.getString(2);
                String dirreccionInf = cursor.getString(3);
                String tipoComparendo = cursor.getString(4);
                String cedulaInf = cursor.getString(5);
                multas.add(new Multa(id,placa,modelo,dirreccionInf,tipoComparendo,cedulaInf));
            }
            cursor.close();
            return multas;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return null;
        }
    }

    public List<Multa> listWithFilter(String column, String value){
        try{
            SQLiteDatabase sql = db.getReadableDatabase();
            Cursor cursor = sql.query(TableMulta.table_name, null, column+"=?",
                    new String[]{value}, null, null, null, null);
            List<Multa> multas = new ArrayList<>();
            while(cursor.moveToNext()){
                long id = cursor.getLong(0);
                String placa = cursor.getString(1);
                String modelo = cursor.getString(2);
                String dirreccionInf = cursor.getString(3);
                String tipoComparendo = cursor.getString(4);
                String cedulaInf = cursor.getString(5);
                multas.add(new Multa(id,placa,modelo,dirreccionInf,tipoComparendo,cedulaInf));
            }
            cursor.close();
            return multas;
        }catch (Exception e){
            Log.e(getClass().getName(), Log.getStackTraceString(e));
            return null;
        }
    }


}
