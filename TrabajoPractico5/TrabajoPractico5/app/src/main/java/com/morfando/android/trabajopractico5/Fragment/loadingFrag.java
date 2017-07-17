package com.morfando.android.trabajopractico5.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.morfando.android.trabajopractico5.MainActivity;
import com.morfando.android.trabajopractico5.R;

/**
 * Created by Matias on 7/4/2017.
 */

public class loadingFrag extends Fragment implements View.OnClickListener {
    Button btnComeIn;
    MainActivity main;
    public View onCreateView (LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_loading, group, false);
        main = (MainActivity) getActivity();
        btnComeIn = (Button)toReturn.findViewById(R.id.btnComeIn);

        btnComeIn.setOnClickListener(this);

        return toReturn;
    }

    public void onClick(View v) {
        main.buttonPressed(v);
    }
}
