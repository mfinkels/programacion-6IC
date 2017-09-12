package com.morfando.android.eval2trim;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button datos, lista, cambios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = (Button)findViewById(R.id.btnDatos);
        lista = (Button)findViewById(R.id.btnLista);
        cambios = (Button)findViewById(R.id.btnCambios);

        datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatosSelecionados();
            }
        });
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaSeleccionados();
            }
        });
        cambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambiosSeleccionados();
            }
        });

    }

    private void CambiosSeleccionados() {
        Intent i = new Intent(this,CambiosActivity.class);
        startActivity(i);
    }

    private void ListaSeleccionados() {
        Intent i = new Intent(this,ListaActivity.class);
        startActivity(i);
    }

    private void DatosSelecionados() {
        Intent i = new Intent(this,DatosActivity.class);
        startActivity(i);
    }
}
