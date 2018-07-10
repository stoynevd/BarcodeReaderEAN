package com.example.dendi.barcodereaderean;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText operatorID;
    private EditText warehouseName;
    private EditText barcode;
    private Button buttonLogin;

    DatabaseHelperSQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        operatorID = findViewById(R.id.operatorID);

        warehouseName = findViewById(R.id.warehouseName);

        barcode = findViewById(R.id.barcode);

        buttonLogin = findViewById(R.id.loginButton);

        sqLite = new DatabaseHelperSQLite(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){

                    String operator_id = operatorID.getText().toString();

                    String warehouseNameStr = warehouseName.getText().toString();

                    String barcodeStr = barcode.getText().toString();

                    sqLite.Authenticate(operator_id, warehouseNameStr, barcodeStr);

                }

            }
        });

    }

    public boolean validate() {

        boolean valid = false;

        if (operatorID.getText().toString().isEmpty()){

            operatorID.setError("Invalid Operator ID");

            valid = false;

        }else if (warehouseName.getText().toString().isEmpty()){

            warehouseName.setError("Invalid Warehouse Name");

            valid = false;

        }else if (barcode.getText().toString().isEmpty()){

            barcode.setError("Invalid Barcode");

            valid = false;

        }else{

            valid = true;

        }

        return valid;

    }
}
