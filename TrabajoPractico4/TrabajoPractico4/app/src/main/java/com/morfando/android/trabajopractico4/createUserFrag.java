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

public class createUserFrag extends Fragment implements View.OnClickListener {
    EditText username, password, confirmPassword;
    Button btnCreate, btnBack;
    MainActivity main;

    public View onCreateView (LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_create_user, group, false);
        main = (MainActivity) getActivity();
        btnCreate = (Button)toReturn.findViewById(R.id.btnCreate);
        btnBack = (Button)toReturn.findViewById(R.id.btnBack);
        username = (EditText)toReturn.findViewById(R.id.newUsername);
        password = (EditText)toReturn.findViewById(R.id.newPassword);
        confirmPassword = (EditText)toReturn.findViewById(R.id.confirm_password);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        btnBack.setOnClickListener(this);

        return toReturn;
    }

    private void createUser() {
        main.createUserPressed(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
    }

    public void onClick(View v) {
        main.buttonPressed(v);
    }
}
