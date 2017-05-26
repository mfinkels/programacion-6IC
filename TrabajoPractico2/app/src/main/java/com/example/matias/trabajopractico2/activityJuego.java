package com.example.matias.trabajopractico2;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class activityJuego extends AppCompatActivity {

    ImageButton[] arrBotones;
    TextView nombreJuegador, jugadas, record;
    int cantidadDeJugadas, minimaCantidadDeJugadas,cantJugadas;
    String nombre;

    dbSQLITE accesoBD;
    SQLiteDatabase bd;
    Cursor conjuntoDeRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        arrBotones = new ImageButton[9];

        arrBotones[0] = (ImageButton) findViewById(R.id.boton0);
        arrBotones[1] = (ImageButton) findViewById(R.id.boton1);
        arrBotones[2] = (ImageButton) findViewById(R.id.boton2);
        arrBotones[3] = (ImageButton) findViewById(R.id.boton3);
        arrBotones[4] = (ImageButton) findViewById(R.id.boton4);
        arrBotones[5] = (ImageButton) findViewById(R.id.boton5);
        arrBotones[6] = (ImageButton) findViewById(R.id.boton6);
        arrBotones[7] = (ImageButton) findViewById(R.id.boton7);
        arrBotones[8] = (ImageButton) findViewById(R.id.boton8);

        nombreJuegador = (TextView) findViewById(R.id.nombreJugador);
        jugadas = (TextView) findViewById(R.id.cantidadDeJugadas);
        record = (TextView) findViewById(R.id.cantMinima);

        Bundle PaquetesDeDatos;
        PaquetesDeDatos = this.getIntent().getExtras();
        nombre = PaquetesDeDatos.getString("nombre");
        nombreJuegador.setText(nombre);

        minimaCantidadDeJugadas =  PaquetesDeDatos.getInt("jugadas");
        empezarAJugar();
    }

    public void presionBoton(View ViewRecibida) {

        ImageButton botonpresionado;
        botonpresionado = (ImageButton) ViewRecibida;

        String tagBotonpresionado;
        tagBotonpresionado = botonpresionado.getTag().toString();

        int numeroboton;
        numeroboton = Integer.parseInt(tagBotonpresionado);
        cantidadDeJugadas++;
        jugadas.setText(cantidadDeJugadas + "");

        switch (numeroboton) {
            case 0:
                invertirImagen(arrBotones[0]);
                invertirImagen(arrBotones[1]);
                invertirImagen(arrBotones[3]);
                invertirImagen(arrBotones[4]);
                break;
            case 1:
                invertirImagen(arrBotones[1]);
                invertirImagen(arrBotones[0]);
                invertirImagen(arrBotones[2]);
                invertirImagen(arrBotones[4]);
                break;
            case 2:
                invertirImagen(arrBotones[2]);
                invertirImagen(arrBotones[1]);
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[5]);
                break;
            case 3:
                invertirImagen(arrBotones[3]);
                invertirImagen(arrBotones[0]);
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[6]);
                break;
            case 4:
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[1]);
                invertirImagen(arrBotones[3]);
                invertirImagen(arrBotones[5]);
                invertirImagen(arrBotones[7]);
                break;
            case 5:
                invertirImagen(arrBotones[5]);
                invertirImagen(arrBotones[2]);
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[8]);
                break;
            case 6:
                invertirImagen(arrBotones[6]);
                invertirImagen(arrBotones[3]);
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[7]);
                break;
            case 7:
                invertirImagen(arrBotones[7]);
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[6]);
                invertirImagen(arrBotones[8]);
                break;
            case 8:
                invertirImagen(arrBotones[8]);
                invertirImagen(arrBotones[4]);
                invertirImagen(arrBotones[5]);
                invertirImagen(arrBotones[7]);
                break;
        }



        if (cantidadDeJugadas >= minimaCantidadDeJugadas && minimaCantidadDeJugadas != 0) {
            AlertDialog.Builder perdio = new AlertDialog.Builder(this);
            perdio.setTitle("Perdiste");
            perdio.setMessage("queres reiniciarlo?");
            perdio.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface ganoMensaje, int id) {
                    empezarAJugar();
                }
            });
            perdio.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface ganoMensaje, int id) {
                    Intent i = new Intent(activityJuego.this, activityPrincipal.class);
                    startActivity(i);
                }
            });
            perdio.show();
        }

        boolean gano = true;
        int i = 0;
        while (gano && i <= 7) {
            gano = funciones.imagenEsIgual(arrBotones[i], arrBotones[i + 1]);
            i++;
        }

        if (gano) {

            // guardar en base de datos la minima cantidad de jugadas
            minimaCantidadDeJugadas = cantidadDeJugadas;

            if(abrirBaseDeDatos()){
                ContentValues valores = new ContentValues();
                valores.put("jugadas", minimaCantidadDeJugadas);
                bd.update("usuarios", valores, "nombre='" + nombre +"'", null);
                bd.close();
            }

            AlertDialog.Builder ganoMensaje = new AlertDialog.Builder(this);
            ganoMensaje.setTitle("Ganaste");
            ganoMensaje.setMessage("Mejoraste tu Record! Ahora es " + minimaCantidadDeJugadas + ", queres reiniciarlo?");
            ganoMensaje.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface ganoMensaje, int id) {
                    empezarAJugar();
                }
            });
            ganoMensaje.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface ganoMensaje, int id) {
                    Intent i = new Intent(activityJuego.this, activityPrincipal.class);
                    startActivity(i);
                }
            });
            ganoMensaje.show();
        }


    }

    private void invertirImagen(ImageButton imagen) {

        Drawable.ConstantState imagenCS, dedoArriba;
        dedoArriba = getResources().getDrawable(R.drawable.dedoarriba).getConstantState();
        imagenCS = imagen.getDrawable().getConstantState();


        if (imagenCS.equals(dedoArriba)) {
            imagen.setImageResource(R.drawable.dedoabajo);
        } else {
            imagen.setImageResource(R.drawable.dedoarriba);
        }
    }


    private void empezarAJugar() {
        boolean generarBotones = true;
        int cantidadDedoArriba, cantidadDedoAbajo;
        cantidadDeJugadas = 0;
        jugadas.setText("0");
        if (minimaCantidadDeJugadas != 0) {
            record.setText("Record: " + minimaCantidadDeJugadas);
        }

        while (generarBotones) {
            cantidadDedoArriba = 0;
            cantidadDedoAbajo = 0;
            for (int punteroBoton = 0; punteroBoton <= 8; punteroBoton++) {
                int numero = funciones.numeroAlAzar();
                if (numero == 1) {
                    arrBotones[punteroBoton].setImageResource(R.drawable.dedoarriba);
                    cantidadDedoArriba++;
                } else {
                    arrBotones[punteroBoton].setImageResource(R.drawable.dedoabajo);
                    cantidadDedoAbajo++;
                }
            }

            if (cantidadDedoArriba != 9 && cantidadDedoAbajo != 9) {
                generarBotones = false;
            }
        }
    }

    private boolean abrirBaseDeDatos() {
        accesoBD = new dbSQLITE(this, "baseDeDatos", null, 1);
        bd = accesoBD.getWritableDatabase();
        if (bd != null) {
            return true;
        } else {
            return false;
        }
    }



    }
