package com.example.dendi.barcodereaderean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMSSQLData {

    Connection connection;
    String ConnectionResult = "";
    Boolean isSuccess = false;


    public ArrayList<Product> getProducts(){

        ArrayList<Product> data = new ArrayList<Product>();

        try {

            DatabaseConnectionMSSQL dbms = new DatabaseConnectionMSSQL();

            connection = dbms.CONN();

            if (connection == null){

                ConnectionResult = "Unable to connect to the MSSQL server, please check your Internet connection";

            }else {

                String query = "SELECT * FROM products";

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);

                while (rs.next()){

                    Product product = new Product(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getInt(7));

                    data.add(product);

                }

                ConnectionResult = "Successful";
                isSuccess = true;
                connection.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }

        return data;

    }

    public List<Map<String, String>> getData(){

        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String, String>>();

        try {

            DatabaseConnectionMSSQL dbms = new DatabaseConnectionMSSQL();

            connection = dbms.CONN();

            if (connection == null){

                ConnectionResult = "Unable to connect to the MSSQL server, please check your Internet connection";

            }else {

                String query = "SELECT * FROM products";

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);

                while (rs.next()){

                    Map<String,String> datanum = new HashMap<String, String>();

                    datanum.put("id", String.valueOf(rs.getInt(1)));

                    datanum.put("warehouse_id", rs.getString(2));

                    datanum.put("operator_id", rs.getString(3));

                    datanum.put("warehouseName", rs.getString(4));

                    datanum.put("barcode", rs.getString(5));

                    datanum.put("estQty", String.valueOf(rs.getInt(6)));

                    datanum.put("scanQty", String.valueOf(rs.getInt(7)));

                    data.add(datanum);

                }

                ConnectionResult = "Successful";
                isSuccess = true;
                connection.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }

        return data;

    }

}
