package com.morfando.android.evaluacion1trimestre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActividadResultado extends AppCompatActivity {

    TextView ingresoL, ingresoTextosIguales, ingresoCBO, cantDeCaracteresMasCorto, ingresosIgnorados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_resultado);
        ingresoL = (TextView) findViewById(R.id.ingresoL);
        ingresoCBO = (TextView) findViewById(R.id.ingresoCBO);
        ingresoTextosIguales = (TextView) findViewById(R.id.ingresoTextosIguales);
        cantDeCaracteresMasCorto = (TextView) findViewById(R.id.cantDeCaracteresMasCorto);
        ingresosIgnorados = (TextView) findViewById(R.id.ingresosIgnorados);
        setearResultados();
    }

    public void reiniciar (View viewRecibida) {
        startActivity(new Intent(this, actividadPrincipal.class));
    }

    private void setearResultados() {
        Bundle recibirDatos;
        recibirDatos = getIntent().getExtras();
        int ingresoLResult, ingresoTextosIgualesResult, ingresoCBOResult, cantDeCaracteresMasCortoResult, ingresoIgnoradosResult;

        ingresoLResult = recibirDatos.getInt("ingresoL");
        ingresoTextosIgualesResult = recibirDatos.getInt("textoIguales");
        ingresoCBOResult  = recibirDatos.getInt("cbo");
        cantDeCaracteresMasCortoResult = recibirDatos.getInt("cantidadDeCaracteresMasCorto");
        ingresoIgnoradosResult = recibirDatos.getInt("ingresosIgnorados");

        ingresoL.setText(ingresoLResult + "");
        ingresoTextosIguales.setText(ingresoTextosIgualesResult + "");
        ingresoCBO.setText(ingresoCBOResult + "");
        cantDeCaracteresMasCorto.setText(cantDeCaracteresMasCortoResult + "");
        ingresosIgnorados.setText(ingresoIgnoradosResult + "");
    }
}
