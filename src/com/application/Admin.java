package com.application;

import com.application.view.App;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends JFrame {

    File inputFile = new File("resources\\AdminCredentials");
    File Stocktext = new File("resources\\Stock");


    public void Login(String username, String password, JTabbedPane tabbedPane) {

        String userNameInput = username;
        String passwordInput = password;

        try {
            Scanner in = new Scanner(inputFile);
            while (in.hasNextLine()) {

                String s = in.nextLine();
                String[] sArray = s.split(",");


                if (userNameInput.equals(sArray[0]) && passwordInput.equals(sArray[1])) {
                    JOptionPane.showMessageDialog(null,
                            "Login Successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    tabbedPane.setEnabledAt(1, true);
                    tabbedPane.setSelectedIndex(1);

                    return;

                }

            }
            JOptionPane.showMessageDialog(null,
                    "Invalid Username / Password", "Error",
                    JOptionPane.ERROR_MESSAGE);

            in.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Text File not found", "Error",
                    JOptionPane.ERROR_MESSAGE);


        }

    }

    public void AddItem(String productName, String qty, String price, String barcode) {
        try {

            FileWriter writeStock = new FileWriter(Stocktext, true);

            String dataRow = "";

            dataRow += "\n";

            dataRow += productName;

            dataRow += "|" + qty;

            dataRow += "|" + price;

            dataRow += "|" + barcode;

            writeStock.write(dataRow);
            writeStock.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void RemoveItem(int barcode) {
        try {

            Scanner findLine = new Scanner(Stocktext);
            List<String> lines = new ArrayList<>();

            while (findLine.hasNextLine()) {
                String line = findLine.nextLine();
                if (!line.contains(String.valueOf(barcode))) {
                    lines.add(line);

                }
            }

            FileWriter removeStock = new FileWriter(Stocktext);
            String lastEntry = lines.get(lines.size() - 1);
            for (String line : lines) {
                if (line.equals(lastEntry)) {
                    removeStock.write(line);
                    break;

                }
                removeStock.write(line + "\n");

            }
            removeStock.close();
            findLine.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void EditItem(String PrdName,String barcode) throws IOException {


    }

}









