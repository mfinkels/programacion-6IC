package com.morfando.android.eval2trim;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatosActivity extends AppCompatActivity {

    EditText textoUser;
    Button procesar, volver;

    SQLiteHelper acesso;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        acesso = new SQLiteHelper(this, "Ejemplo", null, 1);


        textoUser = (EditText)findViewById(R.id.textoUser);
        procesar = (Button)findViewById(R.id.btnProcesarDato);
        volver = (Button)findViewById(R.id.btnVolverDatos);
        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesarDato();
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMain();
            }
        });

    }

    private void volverAMain() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    private void procesarDato() {
        String texto;
        texto = textoUser.getText().toString();
        if(texto.compareTo("") != 0){
            add(texto);
            textoUser.setText("");
        }else {
            Toast.makeText(this,"Escriba algo...",Toast.LENGTH_LONG).show();
        }
    }


    private boolean connectToDatabase() {
        db = acesso.getWritableDatabase();
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
                Toast.makeText(this,"Se agrego", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Ya existe", Toast.LENGTH_SHORT).show();
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

}
