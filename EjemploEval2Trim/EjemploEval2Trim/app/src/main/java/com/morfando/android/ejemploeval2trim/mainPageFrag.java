package com.morfando.android.ejemploeval2trim;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Matias on 8/21/2017.
 */

public class mainPageFrag extends Fragment implements View.OnClickListener {

    MainActivity main;



    Button add, delete, view, exist;
    EditText userEntry;

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle packageOfData){

        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_main_page,group,false);
        main = (MainActivity)getActivity();

        userEntry = (EditText)toReturn.findViewById(R.id.userText);
        add = (Button)toReturn.findViewById(R.id.btnAddDB);
        delete = (Button)toReturn.findViewById(R.id.btnDelete);
        view = (Button)toReturn.findViewById(R.id.btnView);
        exist = (Button)toReturn.findViewById(R.id.btnExist);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        exist.setOnClickListener(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                main.listItemsOnDB();
            }
        });

        return toReturn;
    }

    @Override
    public void onClick(View v) {
        String text = userEntry.getText().toString();
        if (text.compareTo("") == 0){
            Toast.makeText(main, "empty text",Toast.LENGTH_SHORT).show();
        } else {
            switch (v.getId()){
                case R.id.btnAddDB:
                        main.add(text);
                    break;
                case  R.id.btnDelete:
                        main.delete(text);
                    break;
                case  R.id.btnExist:
                        Boolean exist = main.exist(text);
                    if (exist){
                        Toast.makeText(main,"exist",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(main,"doesnt exist",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }



}
