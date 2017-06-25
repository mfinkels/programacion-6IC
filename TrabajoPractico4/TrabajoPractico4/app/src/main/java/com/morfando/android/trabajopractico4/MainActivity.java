package com.morfando.android.trabajopractico4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentManager admin;
    FragmentTransaction trans;

    database accesoBD;
    SQLiteDatabase bd;
    Cursor conjuntoDeRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment loading;
        loading = new loadingPageFrag();

        admin = getSupportFragmentManager();
        trans = admin.beginTransaction();
        trans.replace(R.id.hostFrag, loading);
        trans.commit();
    }

    private boolean abrirBaseDeDatos() {
        accesoBD = new database(this, "baseDeDatos", null, 1);
        bd=accesoBD.getWritableDatabase();
        if (bd != null){
            return true;
        }else {
            return false;
        }
    }

    private void updateFragment(Fragment newFragment){
        trans = admin.beginTransaction();
        trans.replace(R.id.hostFrag, newFragment);
        trans.commit();
    }

    public void buttonPressed(View v) {
        Fragment fm;
        switch (v.getId()){
            case R.id.btnComeIn:
                fm = new logInFrag();
                break;
            case R.id.btnCreate:
                fm = new createUserFrag();
                break;
            case R.id.btnBack:
                fm = new logInFrag();
                break;
            default:
                fm = new loadingPageFrag();
                break;
        }
        updateFragment(fm);
    }

    public void logInPressed(String username, String password) {
        Fragment listUser;
        listUser = new listUserFrag();
        updateFragment(listUser);
        /*
        if (abrirBaseDeDatos()){
            String query = "select * From user Where username='" + username + "' AND password='" + password + "'";
            conjuntoDeRegistros = bd.rawQuery(query, null);
            if (conjuntoDeRegistros.moveToFirst()){
            //se logeo

            } else {
                Toast logInError;
                logInError = Toast.makeText(this, "Username or Password incorrect", Toast.LENGTH_LONG);
                logInError.show();
            }
            bd.close();
        }*/
    }

    public void createUserPressed(String username, String password, String confirmPassword) {
        String error="";
        if(password.compareTo(confirmPassword) != 0){
            error += "password are diferences.";
        }
        String query = "select * From user Where username='" + username + "'";
        conjuntoDeRegistros = bd.rawQuery(query, null);
        if (conjuntoDeRegistros.moveToFirst()){
            error += " username exist";
        }


        if(error.length() > 0){
            Toast errorCreateUser;
            errorCreateUser =Toast.makeText(this,error, Toast.LENGTH_LONG);
            errorCreateUser.show();
        } else {

            if (abrirBaseDeDatos()) {
                ContentValues registro;
                registro = new ContentValues();
                registro.put("username", username);
                registro.put("password", password);
                bd.insert("user", null, registro);
                bd.close();
                Fragment logIn;
                logIn = new logInFrag();
                updateFragment(logIn);
            } else {
                Toast.makeText(this, "Error connecting to database", Toast.LENGTH_LONG).show();
            }

        }
    }
}
