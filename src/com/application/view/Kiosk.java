package com.application.view;


import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Kiosk {

    private static final File ReadStocktext = new File("resources\\Stock");

    public static void Scan(String scanbarcode, JTextArea kioskList, JTextField totalText){

        String BarcodeInput = scanbarcode;
        int quantity = 1;
        float total = 0.00f;


try{

    Scanner in = new Scanner(ReadStocktext);

    while (in.hasNextLine()){
        String s = in.nextLine();
        String[] sArray = s.split("\\|");


        if(BarcodeInput.equals(sArray[3])){

            kioskList.append( sArray[0] + " " + quantity + " " + sArray[2] + "\n");

             total = Float.parseFloat(sArray[2]) + Float.parseFloat(totalText.getText());

            totalText.setText(String.valueOf(total));

        }

    }

} catch (Exception e) {
    e.printStackTrace();
}

    }
}
