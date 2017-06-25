package com.morfando.android.trabajopractico4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matias on 6/15/2017.
 */

public class database extends SQLiteOpenHelper {
    public database(Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version) {
        super(contexto, nombre, fabrica, version);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sqlCrearTablaUsuarios;
        sqlCrearTablaUsuarios = "create table user (username text, password text, lastDate text)";
        bd.execSQL(sqlCrearTablaUsuarios);
    }
    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {

    }
}
