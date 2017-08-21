package com.morfando.android.ejemploeval2trim;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Matias on 8/21/2017.
 */

public class listviewFrag extends Fragment{

    MainActivity main;

    ListView lv;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle packageOfData){

        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_main_page,group,false);
        main = (MainActivity)getActivity();

        lv = (ListView)toReturn.findViewById(R.id.listview);



        return toReturn;
    }
}
