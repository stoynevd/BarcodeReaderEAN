package com.example.dendi.barcodereaderean;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionMSSQL {

    String ip = "51.15.133.200";

    String db = "warehouse";

    String un = "root";

    String password = "7RYm5HNzWVmWARuF";

    @SuppressLint("NewApi")
    public Connection CONN() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Connection conn = null;

        String ConnURL = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            ConnURL = "jdbc:mysql://" + ip + ":3306" + "/" + db;

            conn =  DriverManager.getConnection(ConnURL, un, password);

        } catch (SQLException se) {

            Log.e("ERROR", se.getMessage());

        } catch (ClassNotFoundException e) {

            Log.e("ERROR", e.getMessage());

        } catch (Exception e) {

            Log.e("ERROR", e.getMessage());

        }

        return conn;

    }
}
