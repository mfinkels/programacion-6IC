package com.morfando.android.trabajopractico5.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.morfando.android.trabajopractico5.MainActivity;
import com.morfando.android.trabajopractico5.R;

/**
 * Created by Matias on 7/4/2017.
 */

public class createUserFrag extends Fragment implements View.OnClickListener {
    EditText username, password, confirmPassword, weight;
    CheckBox esHombre;
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
        weight = (EditText)toReturn.findViewById(R.id.weight);
        esHombre = (CheckBox)toReturn.findViewById(R.id.esHombre);


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
        main.createUserPressed(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString(), Integer.parseInt(weight.getText().toString()), esHombre.isChecked());
    }

    public void onClick(View v) {
        main.buttonPressed(v);
    }
}
