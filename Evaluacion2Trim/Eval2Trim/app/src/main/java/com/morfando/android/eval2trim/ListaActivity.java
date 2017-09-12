package com.morfando.android.eval2trim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    ListView lv;
    String[] peliculas;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        peliculas = new String[]{"Star Wars", "Star Wars II", "Star Wars III", "Forest Run", "Toy Story", "Cars", "Facebook Social Network", "HangOver", "Hangover 2", "Emoji La Pelicula"};

        lv = (ListView)findViewById(R.id.listviewPeliculas);
        AdapterPeliculas adapter = new AdapterPeliculas(this, peliculas);
        lv.setAdapter(adapter);

        volver = (Button)findViewById(R.id.btnVolverLista);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMain();
            }
        });

    }

    private void volverAMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
