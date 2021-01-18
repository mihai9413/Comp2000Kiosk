package com.application.controller;

import com.application.KeyValuePair;

public abstract class AbstractController {
    public static final String PRODUCT_NAME = "ProductName";
    public static final String QUANTITY = "Quantity";
    public static final String PRICE = "Price";
    public static final String BARCODE = "Barcode";


    public abstract void setModelProperty(KeyValuePair data);
    public abstract void updateView(KeyValuePair data);

    public void swapModel(int index) { }

}
