package com.morfando.android.trabajopractico5.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.morfando.android.trabajopractico5.Class.User;
import com.morfando.android.trabajopractico5.Fragment.Adapter.userAdapter;
import com.morfando.android.trabajopractico5.MainActivity;
import com.morfando.android.trabajopractico5.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Matias on 7/4/2017.
 */

public class listUserFrag extends Fragment implements View.OnClickListener {

    MainActivity main;
    ArrayList<User> myUser;

    TextView signOut;
    ListView lv;


    public View onCreateView (LayoutInflater inflater, ViewGroup group, Bundle data) {
        View toReturn;
        toReturn = inflater.inflate(R.layout.frag_user_list_view, group, false);
        main = (MainActivity) getActivity();

        signOut = (TextView) toReturn.findViewById(R.id.signOut);
        signOut.setOnClickListener(this);

        lv = (ListView)toReturn.findViewById(R.id.listUser);
        userAdapter adapter = new userAdapter(myUser, main);
        lv.setAdapter(adapter);
        return toReturn;
    }

    public void onClick(View v) {
        main.buttonPressed(v);
    }


    private class userExist extends AsyncTask<String, Void, ArrayList<User>> {

        protected void onPostExecute(ArrayList<User> exist) {
            super.onPostExecute(exist);
            if (exist.size() > 0) {
                myUser = exist;
            }
        }

        @Override
        protected ArrayList<User> doInBackground(String... parametros) {
            String query = parametros[0];
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String connectionInfo = "jdbc:mysql://10.0.2.2:3306/tp5prog";
                Connection conn = DriverManager.getConnection(connectionInfo, "root", "root");

                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery(query);
                ArrayList<User> users = new ArrayList<User>();
                if (result.first()){
                    do {
                        User u = new User();
                        u.username = result.getString("username");
                        u.password = result.getString("password");
                        u.weight = result.getInt("weight");
                        u.esHombre =getBooleanEsHombre(result.getInt("esHombre"));
                        users.add(u);
                    }while (result.next());
                }
                return users;
            }
            catch (ClassNotFoundException e){
                Log.d("Error Class", e.getMessage());
            }
            catch (SQLException e){
                Log.d("Error Sql", e.getMessage());
            }
            return null;
        }

        private Boolean getBooleanEsHombre(int i){
            if (i == 1){
                return true;
            }
            else {
                return false;
            }
        }

    }


}
