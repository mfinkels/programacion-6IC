package com.morfando.android.trabajopractico5;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.morfando.android.trabajopractico5.Fragment.createUserFrag;
import com.morfando.android.trabajopractico5.Fragment.listUserFrag;
import com.morfando.android.trabajopractico5.Fragment.loadingFrag;
import com.morfando.android.trabajopractico5.Fragment.logInFrag;

public class MainActivity extends AppCompatActivity {

    FragmentManager admin;
    FragmentTransaction trans;


    Boolean exist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment loading;
        loading = new loadingFrag();

        admin = getSupportFragmentManager();
        trans = admin.beginTransaction();
        trans.replace(R.id.hostFrag, loading);
        trans.commit();
    }

    private void updateFragment(Fragment newFragment){
        trans = admin.beginTransaction();
        trans.replace(R.id.hostFrag, newFragment);
        trans.commit();
    }

    public void buttonPressed(View v) {
        Fragment fm;
        switch (v.getId()){
            case R.id.btnComeIn:
                fm = new logInFrag();
                break;
            case R.id.btnCreate:
                fm = new createUserFrag();
                break;
            default:
                fm = new loadingFrag();
                break;
        }
        updateFragment(fm);
    }

    public void logInPressed(String username, String password) {
        new existDB().execute("SELECT * FROM user WHERE username='" + username + "' AND password='" + password +"'");
        if (exist){
            updateFragment(new listUserFrag());
        }else{
            Toast.makeText(this, "Error al logearse", Toast.LENGTH_SHORT).show();
        }
    }

    public void createUserPressed(String username, String password, String confirmPassword, int weight, boolean esHombre) {

        new existDB().execute("SELECT * FROM user WHERE username='" + username + "'");

        while (exist == null){}
        if (password.compareTo(confirmPassword) != 0 || exist){
            Toast.makeText(this, "Error al registrarse", Toast.LENGTH_SHORT).show();
        } else {
            String query = "INSERT INTO user (username, password, weight, sex) VALUES  ('" + username + "','" + password + "'," + weight + "," + getSex(esHombre) + ")";
            new insertIntoQuery().execute(query);
            updateFragment(new logInFrag());
        }
    }

    private int getSex(boolean esHombre){
        if (esHombre){
            return 1;
        }
        else {
            return 0;
        }
    }

    private class existDB extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPostExecute(Boolean ex) {
            exist = ex;
            Log.d("Asynctask","llego al onpost" +ex + "");
            super.onPostExecute(ex);

        }

        @Override
        protected Boolean doInBackground(String... parametros) {
            String query = parametros[0];
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String connectionInfo = "jdbc:mysql://10.0.2.2:3306/tp5prog";
                Connection conn = DriverManager.getConnection(connectionInfo, "root", "root");

                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery(query);

                if (result.first()){
                    Log.d("Asynctask","true");
                    return true;
                }
                Log.d("Asynctask","false");

                return false;

            }
            catch (ClassNotFoundException e){
                Log.d("Error Class", e.getMessage());
            }
            catch (SQLException e){
                Log.d("Error Sql", e.getMessage());
            }
            return false;
        }

    }


    private class insertIntoQuery extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... parametros) {
            String query = parametros[0];
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String connectionInfo = "jdbc:mysql://10.0.2.2:3306/tp5prog";
                Connection conn = DriverManager.getConnection(connectionInfo, "root", "root");

                Statement stmt = conn.createStatement();

                Boolean result = stmt.execute(query);
                return result;

            } catch (ClassNotFoundException e) {
                Log.d("Error Class", e.getMessage());
            } catch (SQLException e) {
                Log.d("Error Sql", e.getMessage());
            }
            return false;
        }

    }

}
