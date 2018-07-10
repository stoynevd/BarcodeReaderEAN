package com.example.dendi.barcodereaderean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperSQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "warehouse";

    private static final String TABLE_PRODUCTS = "products";

    private static final String KEY_ID = "id";

    private static final String KEY_BARCODE = "barcode";

    private static final String KEY_WAREHOUSE_ID = "warehouse_id";

    private static final String KEY_WAREHOUSE_NAME = "warehouseName";

    private static final String KEY_OPERATOR_ID = "operator_id";

    private static final String KEY_ESTQTY = "estQty";

    private static final String KEY_SCANQTY = "scanQty";

    public DatabaseHelperSQLite(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PRODUCTS_TABLE = " CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " NUMBER PRIMARY KEY UNIQUE, "
                + KEY_WAREHOUSE_ID + " TEXT , "
                + KEY_OPERATOR_ID + " TEXT, "
                + KEY_WAREHOUSE_NAME + " TEXT, "
                + KEY_BARCODE + " TEXT, "
                + KEY_ESTQTY + " NUMBER, "
                + KEY_SCANQTY + " NUMBER " + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);

    }

    public void addProduct(Product product){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, product.getId());

        values.put(KEY_WAREHOUSE_ID, product.getWarehouse_id());

        values.put(KEY_OPERATOR_ID, product.getOperator_id());

        values.put(KEY_WAREHOUSE_NAME, product.getWarehouseName());

        values.put(KEY_BARCODE, product.getBarcode());

        values.put(KEY_ESTQTY, product.getEstQty());

        values.put(KEY_SCANQTY, product.getScanQty());

        db.insert(TABLE_PRODUCTS, null, values);

        db.close();

    }

    public Product getProduct(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{KEY_ID, KEY_BARCODE, KEY_WAREHOUSE_ID, KEY_WAREHOUSE_NAME, KEY_OPERATOR_ID, KEY_ESTQTY, KEY_SCANQTY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, KEY_ID, null);

        if (cursor != null){

            cursor.moveToFirst();

        }

        Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));

        return product;
    }

    public List<Product> getAllProducts(){

        List<Product> productList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){

            do {

                Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));

                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setBarcode(cursor.getString(1));
                product.setWarehouse_id(cursor.getString(2));
                product.setWarehouseName(cursor.getString(3));
                product.setOperator_id(cursor.getString(4));
                product.setEstQty(Integer.parseInt(cursor.getString(5)));
                product.setScanQty(Integer.parseInt(cursor.getString(6)));

            }while (cursor.moveToNext());


        }

        return productList;
    }


    public int updateProduct(Product product){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, product.getId());
        values.put(KEY_WAREHOUSE_ID, product.getWarehouse_id());
        values.put(KEY_OPERATOR_ID, product.getOperator_id());
        values.put(KEY_WAREHOUSE_NAME, product.getWarehouseName());
        values.put(KEY_BARCODE, product.getBarcode());
        values.put(KEY_ESTQTY, product.getEstQty());
        values.put(KEY_SCANQTY, product.getScanQty());

        return db.update(TABLE_PRODUCTS, values, KEY_ID + "=?",
                new String[]{String.valueOf(product.getId())});

    }


    public void deleteProduct(Product product){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCTS, KEY_ID + "=?",
                new String[]{String.valueOf(product.getId())});

        db.close();

    }

    public int getProductCount(){

        String countQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        cursor.close();

        return cursor.getCount();

    }

    public void deleteAllElements(){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_PRODUCTS);

    }

    public void Authenticate(String operator_id, String warehouseName, String barcode) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE products.operator_id = '" +  operator_id + "'"
                + " AND products.warehouseName = '" + warehouseName + "'"
                + " AND products.barcode = '" + barcode + "'" , null);

        if (cursor.moveToFirst()){

            do {

                Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));

                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setBarcode(cursor.getString(1));
                product.setWarehouse_id(cursor.getString(2));
                product.setWarehouseName(cursor.getString(3));
                product.setOperator_id(cursor.getString(4));
                product.setEstQty(Integer.parseInt(cursor.getString(5)));
                product.setScanQty(Integer.parseInt(cursor.getString(6)));

            }while (cursor.moveToNext());

        }

    }
}
