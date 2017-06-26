package com.morfando.android.trabajopractico4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Matias on 6/25/2017.
 */

public class listUserFrag extends Fragment implements View.OnClickListener{
    MainActivity main;
    ArrayList<String> myUser;

    TextView signOut;
    ListView lv;

    database accesoBD;
    SQLiteDatabase bd;
    Cursor conjuntoDeRegistros;


    public View onCreateView (LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.list_user, group, false);
        main = (MainActivity) getActivity();

        signOut = (TextView) toReturn.findViewById(R.id.signOut);
        signOut.setOnClickListener(this);

        myUser = listUser();
        lv = (ListView) toReturn.findViewById(R.id.listUser);
        AdapterUser adapter = new AdapterUser(myUser, main);
        lv.setAdapter(adapter);
        return toReturn;
    }

    public void onClick(View v) {
        main.buttonPressed(v);
    }

    public ArrayList<String> listUser(){

        ArrayList<String> listUser = new ArrayList<>();
        if (abrirBaseDeDatos()) {
            Cursor conjuntoDeRegistros = bd.rawQuery("select username from user", null);
            if (conjuntoDeRegistros.moveToFirst()) {

                do {
                    listUser.add(conjuntoDeRegistros.getString(0));
                }
                while (conjuntoDeRegistros.moveToNext());
            }
        }
        return listUser;
    }

    private boolean abrirBaseDeDatos() {
        accesoBD = new database(getActivity(), "baseDeDatos", null, 1);
        bd=accesoBD.getWritableDatabase();
        if (bd != null){
            return true;
        }else {
            return false;
        }
    }
}


