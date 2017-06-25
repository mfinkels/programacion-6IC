package com.morfando.android.trabajopractico4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matias on 6/25/2017.
 */

public class AdapterUser extends BaseAdapter {
    private ArrayList<User> users;
    private Context myContext;

    public AdapterUser (ArrayList<User> listUser, Context usedContext) {
        users = listUser;
        myContext = usedContext;
    }

    public int getCount() {
        return users.size();
    }

    public User getItem(int position) {
        User myUser = new User();
        myUser = users.get(position);
        return myUser;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;
        returnView = null;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.list_user_item, groupActual, false);

        TextView username;
        username = (TextView)returnView.findViewById(R.id.username);

        String userFromActualPosition;
        userFromActualPosition = getItem(positionActual).username;
        username.setText(userFromActualPosition);


        TextView date;
        date = (TextView)returnView.findViewById(R.id.lastDate);

        String dateFromActualPosition;
        dateFromActualPosition = getItem(positionActual).date;
        date.setText(dateFromActualPosition);

        return returnView;
    }
}
