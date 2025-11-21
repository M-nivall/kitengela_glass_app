package com.example.Varsani.Suppliers.Model;

public class MyRequetsModel {

    private String requestID;
    private String items;
    private String requestDate;
    private String requestStatus;
    private String quantity;

    private String quantity_price;
    private String bid_approval;


    public MyRequetsModel(String requestID, String items,
                          String requestDate, String requestStatus, String quantity, String quantity_price, String bid_approval) {
        this.requestID = requestID;
        this.items = items;
        this.quantity= quantity;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.quantity_price = quantity_price;
        this.bid_approval = bid_approval;
    }

    public String getRequestID() {
        return requestID;
    }


    public String getItems() {
        return items;
    }

    public String getQuantity() {

        return quantity;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getQuantity_price() {
        return quantity_price;
    }

    public String getBid_approval() {
        return bid_approval;
    }
}
