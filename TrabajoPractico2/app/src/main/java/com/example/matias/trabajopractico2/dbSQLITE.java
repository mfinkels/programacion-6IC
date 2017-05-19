package com.example.matias.trabajopractico2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

/**
 * Created by Matias on 5/16/2017.
 */

public class dbSQLITE extends SQLiteOpenHelper {

    public dbSQLITE(Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version) {
        super(contexto, nombre, fabrica, version);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sqlCrearTablaUsuarios;
        sqlCrearTablaUsuarios = "create table usuarios (nombre text, jugadas integer)";
        bd.execSQL(sqlCrearTablaUsuarios);
    }
    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {

    }



}
