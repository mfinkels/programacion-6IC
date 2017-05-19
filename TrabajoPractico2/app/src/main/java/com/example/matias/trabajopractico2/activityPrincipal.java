package com.example.matias.trabajopractico2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activityPrincipal extends AppCompatActivity {

    Button btnInciar;
    EditText nombreIngresado, resultado;
    TextView cuenta;
    int numero1, numero2;

    dbSQLITE accesoBD;
    SQLiteDatabase bd;
    Cursor conjuntoDeRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnInciar = (Button) findViewById(R.id.botonIniciarJuego);
        nombreIngresado =  (EditText)findViewById(R.id.nombre);
        resultado =  (EditText)findViewById(R.id.resultado);
        cuenta =  (TextView) findViewById(R.id.cuenta);
        btnInciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
        numero1 = funciones.numerosAlAzar();
        numero2 = funciones.numerosAlAzar();
        cuenta.setText(numero1 + " + " + numero2 + "=");
    }

    private void iniciar() {

        String nombre = nombreIngresado.getText().toString();
        Intent i = new Intent(this, activityJuego.class);



        if (nombre != ""){
            int cantJugadas = 0;
            if(abrirBaseDeDatos()){
                String consulta = "select nombre, jugadas From usuarios where nombre='" + nombre +"'";
                conjuntoDeRegistros = bd.rawQuery(consulta, null);
                ContentValues obtenerRegistro;
                obtenerRegistro = new ContentValues();
                if (existeUsuario(nombre)){
                    cantJugadas  = conjuntoDeRegistros.getInt(1);
                }else {
                    obtenerRegistro.put("nombre", nombre);
                    obtenerRegistro.put("jugadas", cantJugadas);
                    bd.insert("usuarios", null, obtenerRegistro);
                }
                bd.close();
            }

            Bundle PaqueteDeDatos;
            PaqueteDeDatos = new Bundle();
            PaqueteDeDatos.putString("nombre", nombre);
            PaqueteDeDatos.putInt("jugadas", cantJugadas);
            i.putExtras(PaqueteDeDatos);
            startActivity(i);
        }else {
            Toast.makeText(this, "Ingrese Nombre", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean abrirBaseDeDatos() {
        accesoBD = new dbSQLITE(this, "baseDeDatos", null, 1);
        bd=accesoBD.getWritableDatabase();
        if (bd != null){
            return true;
        }else {
            return false;
        }
    }

    private boolean existeUsuario(String nombre) {
        if (conjuntoDeRegistros.moveToFirst() == true) {
            return true;
        }else {
            return false;
        }
    }

}
