package com.morfando.android.trabajopractico4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Matias on 6/13/2017.
 */

public class logInFrag extends Fragment implements View.OnClickListener {

    EditText username, password;
    Button btnLogIn, btnCreate;
    MainActivity main;

    public View onCreateView (LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_log_in, group, false);
        main = (MainActivity) getActivity();
        btnLogIn = (Button)toReturn.findViewById(R.id.btnLogIn);
        btnCreate = (Button)toReturn.findViewById(R.id.btnCreate);
        username = (EditText)toReturn.findViewById(R.id.username);
        password = (EditText)toReturn.findViewById(R.id.password);


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        btnCreate.setOnClickListener(this);

        return toReturn;
    }

    private void logIn() {
        main.logInPressed(username.getText().toString(), password.getText().toString());
    }

    public void onClick(View v){
        main.buttonPressed(v);
    }
}
