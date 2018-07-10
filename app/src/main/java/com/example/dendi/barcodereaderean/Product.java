package com.example.dendi.barcodereaderean;

public class Product {

    private int id;

    private String warehouse_id;

    private String operator_id;

    private String warehouseName;

    private String barcode;

    private int estQty;

    private int scanQty;


    public Product(int id, String warehouse_id, String operator_id, String warehouseName, String barcode, int estQty, int scanQty){

        this.id = id;

        this.warehouse_id = warehouse_id;

        this.operator_id = operator_id;

        this.warehouseName = warehouseName;

        this.barcode = barcode;

        this.estQty = estQty;

        this.scanQty = scanQty;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getEstQty() {
        return estQty;
    }

    public void setEstQty(int estQty) {
        this.estQty = estQty;
    }

    public int getScanQty() {
        return scanQty;
    }

    public void setScanQty(int scanQty) {
        this.scanQty = scanQty;
    }

    @Override
    public String toString() {
        return "Product --> " +
                "id=" + id +
                ", warehouse_id='" + warehouse_id + '\'' +
                ", operator_id='" + operator_id + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", estQty=" + estQty +
                ", scanQty=" + scanQty;
    }
}
