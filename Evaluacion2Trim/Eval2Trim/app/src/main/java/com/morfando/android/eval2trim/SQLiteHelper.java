package com.morfando.android.eval2trim;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Matias on 8/22/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String Nombre, SQLiteDatabase.CursorFactory fabrica, int version)
    {
        super(context, Nombre, fabrica,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQLite","Declaro e inicializo la variable para crear la tabla");
        String sqlCrearTabla;
        sqlCrearTabla= "create table Ejemplo(campo1 text)";
        Log.d("SQLite" , "Invoco creador de tabla");
        db.execSQL(sqlCrearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
