package com.morfando.android.eval2trim;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Matias on 8/22/2017.
 */

public class CambiosResultadoFrag extends Fragment {
    CambiosActivity cambios;

    TextView resultado;
    Button volver;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle packageOfData){

        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_cambio_resultado,group,false);
        cambios = (CambiosActivity) getActivity();

        resultado = (TextView)toReturn.findViewById(R.id.resultadoCambios);
        String res = cambios.getResultado();
        resultado.setText(res);

        volver = (Button)toReturn.findViewById(R.id.btnVolverCambios);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambios.BotonPresionado(v);
            }
        });


        return toReturn;
    }
}
