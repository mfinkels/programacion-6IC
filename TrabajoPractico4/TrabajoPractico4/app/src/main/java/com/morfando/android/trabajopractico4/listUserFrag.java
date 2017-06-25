package com.morfando.android.trabajopractico4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matias on 6/25/2017.
 */

public class listUserFrag extends Fragment implements View.OnClickListener{
    MainActivity main;
    ArrayList<User> myUser;

    TextView signOut;
    ListView lv;

    public View onCreateView (LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.list_user, group, false);
        main = (MainActivity) getActivity();

        signOut = (TextView) toReturn.findViewById(R.id.signOut);
        signOut.setOnClickListener(this);

        lv = (ListView) toReturn.findViewById(R.id.listUser);
        AdapterUser adapter = new AdapterUser(myUser, main);

        lv.setAdapter(adapter);



        return toReturn;
    }

    public void onClick(View v) {
        main.buttonPressed(v);
    }
}
