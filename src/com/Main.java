package com;
import com.application.controller.*;
import com.application.model.*;
import com.application.view.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static App appView;
    public static String filePath = "resources\\Stock";
    public static String separator = "\\|";

    public static void main(String[] args) {

        appView = new App();
         load();


    }

    public static void load() {

        int counter = 0;
        ModelSubject[] models = new ModelSubject[counter+1];

        try {
            File file = new File(filePath);

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                String[] dataStock = data.split(separator);

                models[counter] = new ModelSubject();

                models[counter].setProductName(dataStock[0]);

//
                int QtyToInt = Integer.parseInt(dataStock[1]);
                models[counter].setQuantity(QtyToInt);



                float PriceToFloat = Float.parseFloat(dataStock[2]);
                models[counter].setPrice(PriceToFloat);


//
                int BarcodeToInt = Integer.parseInt(dataStock[3]);
                models[counter].setBarcode(BarcodeToInt);


                counter ++;

                models = Arrays.copyOf(models, counter + 1);
            }

            models[counter] = new ModelSubject();

            models[counter].setProductName("New Item");
            models[counter].setQuantity(0);
            models[counter].setPrice(0.00f);
            models[counter].setBarcode(0);

            counter++;
            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] Names = new String[counter];
        for (int i = 0; i < Names.length; i++) {
            Names[i] = models[i].getProductName();
        }

        AbstractController controller = new MultiModelController(models, appView);
        appView.setupEditorList(Names);

    }


}


