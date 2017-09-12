package com.morfando.android.eval2trim;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CambiosActivity extends AppCompatActivity {

    FragmentManager admin;
    FragmentTransaction transaction;

    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        admin = getSupportFragmentManager();

        updateFragment(new CambiosPrincipalFrag());
    }
    private void updateFragment(Fragment fm){
        transaction = admin.beginTransaction();
        transaction.replace(R.id.FragmentConteiner, fm);
        transaction.commit();
    }

    public void mostrarResulado(String res) {
        resultado = res;
        updateFragment(new CambiosResultadoFrag());
    }

    public String getResultado(){
        return resultado;
    }

    public void BotonPresionado(View v) {
        int id = v.getId();
        if (id == R.id.btnVolverCambios) {
            updateFragment(new CambiosPrincipalFrag());
        } else if (id == R.id.btnVolverAMain){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
