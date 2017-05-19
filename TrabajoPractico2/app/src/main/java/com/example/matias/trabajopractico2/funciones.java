package com.example.matias.trabajopractico2;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

import java.util.Random;

/**
 * Created by Matias on 5/11/2017.
 */

public class funciones {

    public static boolean imagenEsIgual(ImageButton primerImagen, ImageButton segundaImagen) {
        boolean resultado;

        Drawable.ConstantState codigoPrimerImagen;
        Drawable.ConstantState codigoSegundoImagen;

        codigoPrimerImagen = primerImagen.getDrawable().getConstantState();
        codigoSegundoImagen = segundaImagen.getDrawable().getConstantState();

        if (codigoPrimerImagen.equals(codigoSegundoImagen)) {
            resultado = true;
        } else {
            resultado = false;
        }

        return resultado;
    }


    public static int numeroAlAzar() {
        int resultado;
        new Random();

        Random generadorDeAzar;
        generadorDeAzar = new Random();

        resultado = generadorDeAzar.nextInt(2);

        return resultado;
    }

    public static int numerosAlAzar() {
        int resultado;
        new Random();

        Random generadorDeAzar;
        generadorDeAzar = new Random();

        resultado = generadorDeAzar.nextInt(9);

        return resultado;
    }

}
