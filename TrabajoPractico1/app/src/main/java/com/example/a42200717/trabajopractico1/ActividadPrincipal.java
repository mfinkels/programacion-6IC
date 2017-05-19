package com.example.a42200717.trabajopractico1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        AdView adView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

    }

    public void ButtonClick(View viewRecibida) {

        // Traemos lo ingresado por el usuario de la Activity
        EditText primerTextoIngresado;
        primerTextoIngresado = (EditText) findViewById(R.id.primerTexto);

        EditText segundoTextoIngresado;
        segundoTextoIngresado = (EditText) findViewById(R.id.segundoTexto);

        String primerTexto;
        primerTexto = primerTextoIngresado.getText().toString();

        String segundoTexto;
        segundoTexto = segundoTextoIngresado.getText().toString();

        int primerLargo;
        primerLargo = primerTexto.length();

        int segundoLargo;
        segundoLargo = segundoTexto.length();

        Toast miMensaje;
        String mensajeAMostrar;

        if (primerTexto.length() == 0) {
            mensajeAMostrar = "En el primer texto no se ingreso nada.";
        } else {
            mensajeAMostrar = "En el primer texto ingreso: " + primerLargo + " caracteres.";
        }

        if (segundoTexto.length() == 0) {
            mensajeAMostrar += "En el segundo texto no se ingreso nada.";
        } else {
            mensajeAMostrar += "En el segundo texto ingreso: " + segundoLargo + " caracteres.";
        }

        miMensaje = Toast.makeText(this, mensajeAMostrar, Toast.LENGTH_LONG);
        miMensaje.show();

        int resultadoResta;
        String diferenciaDeCaracteres;

        if (primerLargo > segundoLargo) {
            resultadoResta = primerLargo - segundoLargo;
            diferenciaDeCaracteres="El texto " + primerTexto + " tiene " + resultadoResta + " carecteres más que " + segundoTexto;
        } else {
            if (primerLargo < segundoLargo) {
                resultadoResta = segundoLargo - primerLargo;
                diferenciaDeCaracteres="El texto " + segundoTexto + " tiene " + resultadoResta + " carecteres más que " + primerTexto;
            } else {
                resultadoResta = 0;
                diferenciaDeCaracteres="Tienen la misma cantidad de caracteres.";
            }
        }

        String mezclaDeTextos;

        if(primerTexto.length() > 3 && segundoTexto.length()>3){
            mezclaDeTextos=primerTexto.substring(0, 3);
            mezclaDeTextos+= segundoTexto.substring(0,3);
        }
        else{
            mezclaDeTextos="No tiene suficientes caracteres para mezclar los nombres.";
        }


        String respuestaEjBC;
        respuestaEjBC = diferenciaDeCaracteres +  System.getProperty("line.separator") + mezclaDeTextos;

        TextView ResDifDeCarac;
        ResDifDeCarac=(TextView)findViewById(R.id.textoAMostrar);
        ResDifDeCarac.setText(respuestaEjBC);


}

    public void ButtonCaracteresA(View viewRecibida) {
        //Instanciamos las variables
        String textoConA;
        TextView textoIngresado;

        //Traemos del .xml a java
        textoIngresado=(TextView)findViewById(R.id.textoConA);
        textoConA= textoIngresado.getText().toString();
        textoConA=textoConA.toUpperCase();
        //Comenzamos  a recorrer el TextView Ingresado
        int comparacion, contador;
        contador=0;
        String caracter;

        for (int i = 0; i<textoConA.length(); i++){
            //agarramos el primer caracter
            caracter=textoConA.substring(i,i+1);

            //lo comparamos con el caracter A que devuelve 0 si es igual
            comparacion=caracter.compareTo("A");

            if(comparacion==0){
                contador++;
            }
        }

        CheckBox cboSeleccion=(CheckBox)findViewById(R.id.cboMasDe10Caracteres);
        String resultado;

        if(cboSeleccion.isChecked() && contador <10)
        {
            resultado="Advertencia: no hay más de 10 caracteres!!!";
        }
        else {
            resultado="La cantidad de caracteres es " + contador + ".";
        }

        Toast miMensaje;
        miMensaje = Toast.makeText(this, resultado, Toast.LENGTH_LONG);
        miMensaje.show();

    }

    public void ButtonInvertir(View viewRecibida) {

        EditText textoIngresado;
        String texto;

        textoIngresado=(EditText)findViewById(R.id.textoSinInvertir);
        texto =textoIngresado.getText().toString();

        StringBuilder textoAInvertir;
        textoAInvertir= new StringBuilder();
        textoAInvertir.append(texto);
        textoAInvertir.reverse();

        Toast miMensaje;
        miMensaje=Toast.makeText(this,textoAInvertir, Toast.LENGTH_LONG);
        miMensaje.show();
    }

    public void ButtonUltimaPalabra(View viewRecibida) {

        EditText ultimaPalabra;
        String seleccionarUltimaPalabra;

        ultimaPalabra=(EditText)findViewById(R.id.ultimaPalabra);
        seleccionarUltimaPalabra=ultimaPalabra.getText().toString();

        int ultimoEspacio;
        ultimoEspacio = seleccionarUltimaPalabra.lastIndexOf(" ");

        seleccionarUltimaPalabra = seleccionarUltimaPalabra.substring(ultimoEspacio+1);

        Toast ultimaPalabraToast;
        ultimaPalabraToast=Toast.makeText(this , seleccionarUltimaPalabra , Toast.LENGTH_LONG);
        ultimaPalabraToast.show();


    }
    public void bottonLETRA(View jarte) {
        String letraGuardar;
        EditText letraAbuscar;

        String textoaGuardar;
        EditText textoAbuscar;

        letraAbuscar = (EditText) findViewById(R.id.letraAbuscaredit);
        letraGuardar = letraAbuscar.getText().toString();

        textoAbuscar = (EditText) findViewById(R.id.opcional2);
        textoaGuardar = textoAbuscar.getText().toString();

        int comparacion, contador;
        contador = 0;
        String caracter = "";

        String mensaje="";
        Toast mensajeaMostrar;
        for (int i = 0; i < textoaGuardar.length(); i++) {
            //agarramos el primer caracter
            caracter = textoaGuardar.substring(i, i + 1);

            //lo comparamos con el caracter A que devuelve 0 si es igual
            comparacion = caracter.compareTo(letraGuardar);

            if (comparacion == 0) {
                contador++;
            }

        }
        mensaje="La cantidad de veces q se repite la letra " +letraGuardar.toString()+" es de "+ contador;
        mensajeaMostrar=Toast.makeText(this, mensaje , Toast.LENGTH_LONG);
        mensajeaMostrar.show();
    }
    public void bottoncapicua(View Petimasvaleqtegustenlosnombresporquetemato){

        EditText capic;
        String capicuatexto;

        capic=(EditText)findViewById(R.id.capicua);
        capicuatexto=capic.getText().toString();
        String textoInverso="";


        StringBuilder textoAInvertir;

        textoAInvertir= new StringBuilder();
        textoAInvertir.append(capicuatexto);
        textoAInvertir.reverse();
        textoInverso=textoAInvertir.toString();

        String mensaje="";
        Toast mensajeaMostrar;

        int comparacion=0;
        comparacion=textoInverso.compareTo(capicuatexto);

        if ( comparacion==0)
        {
            mensaje="el texto es capicua";
        }else{
            mensaje="el texto no es capicua";
        }

        mensajeaMostrar=Toast.makeText(this, mensaje , Toast.LENGTH_LONG);
        mensajeaMostrar.show();


    }
    public  void bottonespacios(View rummmmmm)
    {

        EditText esp;
        String espaciosstr;

        esp=(EditText)findViewById(R.id.espacios);
        espaciosstr=esp.getText().toString();

        int ultimoEspacio;
        ultimoEspacio = espaciosstr.lastIndexOf(" ");

        espaciosstr = espaciosstr.substring(ultimoEspacio);

        Toast ultimaPalabraToast;
        ultimaPalabraToast=Toast.makeText(this , espaciosstr , Toast.LENGTH_LONG);
        ultimaPalabraToast.show();


    }


}





