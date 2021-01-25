package com.application.model;

import com.application.KeyValuePair;
import com.application.controller.AbstractController;

import java.util.ArrayList;

public class ModelSubject implements IModelSubject {
    private String ProductName;
    private int Quantity;
    private float Price;
    private int Barcode;

    private final ArrayList<AbstractController> observers = new ArrayList<>();

    @Override
    public void add(AbstractController observer) {
        observers.add(observer);
    }

    @Override
    public void remove(AbstractController observer) {
        observers.remove(observer);
    }


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
        onPropertyChanged(new KeyValuePair(AbstractController.PRODUCT_NAME, this.ProductName));
//        System.out.println(this.ProductName);
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
        onPropertyChanged(new KeyValuePair(AbstractController.QUANTITY, this.Quantity));
//        System.out.println(this.Quantity);

    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
        onPropertyChanged(new KeyValuePair(AbstractController.PRICE, this.Price));
        System.out.println(this.Price);

    }

    public int getBarcode() {
        return Barcode;
    }

    public void setBarcode(int Barcode) {
        this.Barcode = Barcode;
        onPropertyChanged(new KeyValuePair(AbstractController.BARCODE, this.Barcode));
        System.out.println(this.Barcode);
    }


    @Override
    public void onPropertyChanged(KeyValuePair data) {
        for(AbstractController observer : observers) {
            observer.updateView(data);
        }
    }
}
