package com.example.dendi.barcodereaderean;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://51.15.133.200/warehouse";
    private static final String user = "root";
    private static final String password = "7RYm5HNzWVmWARuF";

    private DatabaseHelperSQLite sqlLite;

    private Button loadAllDataFromMStoLiteBtn;

    private TextView viewElements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlLite = new DatabaseHelperSQLite(this);

        loadAllDataFromMStoLiteBtn = (Button) findViewById(R.id.loadAllDataFromMStoLiteBtn);

        viewElements = (TextView) findViewById(R.id.viewAddedElementsView);

        ArrayList<Product> products = new ArrayList<Product>();

        GetMSSQLData MSSQLData = new GetMSSQLData();

        products = MSSQLData.getProducts();

        final ArrayList<Product> finalProducts = products;
        loadAllDataFromMStoLiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (Product product : finalProducts){

                    if (finalProducts.contains(product)){

                        //already added

                    }else {

                        sqlLite.addProduct(product);

                    }
                }

//                loadAllDataFromMStoLiteBtn.setEnabled(false);
//                loadAllDataFromMStoLiteBtn.setVisibility(View.GONE);

                    createNewActivity();

            }
        });

    }

    private void createNewActivity(){

        Intent startNewActivity = new Intent(this, LoginActivity.class);
        startActivity(startNewActivity);

    }

}


