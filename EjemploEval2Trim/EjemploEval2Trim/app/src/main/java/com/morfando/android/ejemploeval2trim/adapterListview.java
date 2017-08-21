package com.morfando.android.ejemploeval2trim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matias on 8/21/2017.
 */

public class adapterListview extends BaseAdapter {

    private Context context;
    private ArrayList<String> data;

    public adapterListview(Context context, ArrayList<String> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
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
        toReturn = inflater.inflate(R.layout.item_listview,parent, false);

        TextView text;
        text = (TextView)toReturn.findViewById(R.id.text);
        String data = getItem(position);
        text.setText(data);

        return null;
    }
}
