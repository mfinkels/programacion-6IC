package com.morfando.android.trabajopractico5.Fragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.morfando.android.trabajopractico5.Class.User;
import com.morfando.android.trabajopractico5.R;

import java.util.ArrayList;

/**
 * Created by Matias on 7/6/2017.
 */

public class userAdapter extends BaseAdapter {
    private ArrayList<User> users;
    private Context myContext;

    public userAdapter (ArrayList<User> listUser, Context usedContext) {
        users = listUser;
        myContext = usedContext;
    }

    public int getCount() {
        return users.size();
    }

    public User getItem(int position) {
        User myUser = users.get(position);
        return myUser;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;
        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.list_user_item, groupActual, false);

        TextView username;
        username = (TextView)returnView.findViewById(R.id.username);

        User myUser = getItem(positionActual);

        return returnView;
    }
}
