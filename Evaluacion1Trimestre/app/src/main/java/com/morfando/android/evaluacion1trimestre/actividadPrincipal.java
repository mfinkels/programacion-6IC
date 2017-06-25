package com.morfando.android.evaluacion1trimestre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class actividadPrincipal extends AppCompatActivity {

    EditText editText1, editText2;
    CheckBox cbo;
    int ingresoL, ingresoConTextoIguales, ingresoConCheckBox, cantidadDeCaracteresMasCorto, ingresosIgnorados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        cbo = (CheckBox) findViewById(R.id.checkbox);


    }

    public void procesamientoDeDatos (View viewRecibida){
        String texto1, texto2;
        texto1 = editText1.getText().toString();
        texto2 = editText2.getText().toString();
        texto1.toLowerCase();
        texto2.toLowerCase();


        // verifico que ambos EditText se haya ingresado texto
        if (texto1.compareTo("") != 0 || texto2.compareTo("") != 0) {
            // Proceso informacion ingresada

            // Muestro resultado en caso de fin
            if (texto1.compareTo("fin") == 0 || texto2.compareTo("fin") == 0) {

                // se muestan resultados
                Bundle packageOfData;
                packageOfData = new Bundle();
                packageOfData.putInt("ingresoL", ingresoL);
                packageOfData.putInt("textoIguales", ingresoConTextoIguales);
                packageOfData.putInt("cbo", ingresoConCheckBox);
                packageOfData.putInt("cantidadDeCaracteresMasCorto", cantidadDeCaracteresMasCorto);
                packageOfData.putInt("ingresosIgnorados", ingresosIgnorados);

                Intent i;
                i = new Intent(this, ActividadResultado.class);
                i.putExtras(packageOfData);
                startActivity(i);
            } else {
                // en caso de que se ingresaron datos para procesarlos, los proceso y guardo en variables.

                // verifico si se ingreso alguna letra 'l' en texto1
                for (int i = 0; i < texto1.length(); i++) {
                    if (texto1.charAt(i) == 'l') {
                        ingresoL++;
                    }
                }

                // verifico si se ingreso alguna letra 'l' en texto2
                for (int i = 0; i < texto2.length(); i++) {
                    if (texto2.charAt(i) == 'l') {
                        ingresoL++;
                    }
                }

                // comparo los textos
                if (texto1.compareTo(texto2) == 0) {
                    ingresoConTextoIguales++;
                }

                // verifico si el cbo esta checkeado
                int cantCaracteresTexto1,cantCaracteresTexto2;
                cantCaracteresTexto1 = texto1.length();
                cantCaracteresTexto2 = texto2.length();
                if (cbo.isChecked() == true) {
                    ingresoConCheckBox++;
                } else if (cantidadDeCaracteresMasCorto == 0 || cantidadDeCaracteresMasCorto > cantCaracteresTexto1 || cantidadDeCaracteresMasCorto > cantCaracteresTexto2) {
                    // si el cbo no esta checkeado y el texto1 o texto2 es mas corto
                    if (texto1.length() <= texto2.length()) {
                        //texto1
                        cantidadDeCaracteresMasCorto = texto1.length();
                    } else {
                        // texto2
                        cantidadDeCaracteresMasCorto = texto2.length();
                    }
                }
                setearEditTextYCB0();
                Toast mensaje;
                mensaje = Toast.makeText(this, "Datos procesados", Toast.LENGTH_SHORT);
                mensaje.show();
            }// fin comparacion de texto a Fin

        } else {
            // Error ya que no se ingreso texto en algunos de los EditText
            ingresosIgnorados++;
            Toast error;
            error = Toast.makeText(this, "Error: ingrese texto.", Toast.LENGTH_LONG);
            error.show();
        }
    }

    private void setearEditTextYCB0() {
        editText1.setText("");
        editText2.setText("");
        cbo.setChecked(false);
    }

    private void inicialiazarContadores() {
        ingresoL = 0;
        ingresoConTextoIguales = 0;
        ingresoConCheckBox = 0;
        cantidadDeCaracteresMasCorto = 0;
        ingresosIgnorados = 0;
    }

}
