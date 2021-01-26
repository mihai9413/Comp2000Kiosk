package com.application.view;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public static void Removefromstock(String productName, String qty, String price, String barcode) {
        try {

            Scanner findLine = new Scanner(ReadStocktext);
            List<String> lines = new ArrayList<>();

            String separator = "\\|";
            String dataRow = "";

            dataRow += productName;

            dataRow += "|" + (Integer.parseInt(qty) - 1);

            dataRow += "|" + price;

            dataRow += "|" + barcode;

            while (findLine.hasNextLine()) {
                String line = findLine.nextLine();

                String[] dataStock = line.split(separator);

                if (line.contains(String.valueOf(barcode))) {

                    lines.add(dataRow);

                }
                else {
                    lines.add(line);

                }
            }

            FileWriter updateStock = new FileWriter(ReadStocktext);
            String updateEntry = lines.get(lines.size()-1 );
            for (String line : lines) {
                if (line.equals(updateEntry))
                {

                    updateStock.write(line);
                    break;

                }

                updateStock.write(line + "\n");

            }
            updateStock.close();
            findLine.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
