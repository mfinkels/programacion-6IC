package com.morfando.android.eval2trim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by Matias on 8/22/2017.
 */

public class AdapterPeliculas extends BaseAdapter {
    private Context context;
    private String[] data;

    public AdapterPeliculas(Context context, String[] data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toReturn;
        toReturn = inflater.inflate(R.layout.item_pelicula,parent, false);

        TextView titulo;
        titulo = (TextView)toReturn.findViewById(R.id.peliculaTitulo);
        String peliculaTitulo = getItem(position);
        titulo.setText(peliculaTitulo);

        TextView caracteres;
        caracteres = (TextView)toReturn.findViewById(R.id.peliculaCaracteres);
        int cant = peliculaTitulo.length();
        caracteres.setText(cant + "");

        return toReturn;
    }
}
