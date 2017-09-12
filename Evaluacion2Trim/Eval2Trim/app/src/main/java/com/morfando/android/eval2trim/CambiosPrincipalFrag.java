package com.morfando.android.eval2trim;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Matias on 8/22/2017.
 */

public class CambiosPrincipalFrag extends Fragment {

    EditText texto1, texto2;
    Button calcular, volver;

    CambiosActivity cambios;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle packageOfData){

        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_cambios_principal,group,false);
        cambios = (CambiosActivity) getActivity();

        texto1 = (EditText)toReturn.findViewById(R.id.texto1);
        texto2 = (EditText)toReturn.findViewById(R.id.texto2);
        calcular = (Button)toReturn.findViewById(R.id.btnCalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PresionoCalcular();
            }
        });

        volver = (Button)toReturn.findViewById(R.id.btnVolverAMain);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambios.BotonPresionado(v);
            }
        });


        return toReturn;
    }

    private void PresionoCalcular() {
        String comparar1, comparar2;
        comparar1 = texto1.getText().toString();
        comparar2 = texto2.getText().toString();
        if (comparar1.length() > comparar2.length()){
            cambios.mostrarResulado(comparar1);
        } else if (comparar2.length() == comparar1.length()){
            cambios.mostrarResulado("Mmm... Son iguales");
        } else {
            cambios.mostrarResulado(comparar2);
        }

    }
}
