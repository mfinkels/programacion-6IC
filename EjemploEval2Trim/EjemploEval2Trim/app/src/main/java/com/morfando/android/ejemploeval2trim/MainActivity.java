package com.morfando.android.ejemploeval2trim;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager admin;
    FragmentTransaction transaction;

    SQLiteHelper access;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = getSupportFragmentManager();

        updateFragment(new mainPageFrag());

    }

    private void updateFragment(Fragment fm){
        transaction = admin.beginTransaction();
        transaction.replace(R.id.FragmentConteiner, fm);
        transaction.commit();
    }

    public void listItemsOnDB() {

    }

    private boolean connectToDatabase() {
        db = access.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;
    }

    public void add(String text) {
        if (!exist(text)){
            if (connectToDatabase()) {
                ContentValues nuevoregistro;
                nuevoregistro = new ContentValues();
                nuevoregistro.put("campo1", text);
                db.insert("Ejemplo", null, nuevoregistro);
                db.close();
            }
        } else {
            Toast.makeText(this,"doesnt exists", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(String text) {
        if (exist(text)){
            if(connectToDatabase()){
                db.delete("Ejemplo","campo1='"+text+"'",null);
                db.close();
            }
        } else {
            Toast.makeText(this,"doesnt exists", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean exist(String text) {
        if (connectToDatabase()) {
            Cursor data = db.rawQuery("SELECT * FROM Ejemplo WHERE campo1='" + text + "'", null);
            if (data.moveToFirst()) {
                return true;
            }
            db.close();
        }
        return false;
    }

    public ArrayList<String> getAll() {
        ArrayList<String> information = new ArrayList<String>();
        if (connectToDatabase()) {
            Cursor data = db.rawQuery("SELECT * FROM Ejemplo", null);
            if (data.moveToFirst()) {
                do {
                    information.add(data.getString(0));
                }while (data.moveToNext());
            }
            db.close();
        }
        return information;
    }
}
