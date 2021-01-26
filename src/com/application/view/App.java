package com.application.view;


import com.Main;
import com.application.Admin;
import com.application.KeyValuePair;
import com.application.Payment;
import com.application.controller.AbstractController;
import com.application.model.ModelSubject;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends AbstractView {

    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel editPanel;
    private JList<String> editList;
    private JTextField ProdName;
    private JTextField qtyField;
    private JTextField priceField;
    private JTextField barcodeField;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton updateButton;
    public JTextField username;
    public JPasswordField password;
    private JButton loginButton;
    private JButton logout;
    private JTextField scanbarcode;
    private JButton scanButton;
    private JTextArea kioskList;
    private JTextField totalText;
    private JButton CARDButton;
    private JButton CASHButton;
    private JButton CANCELButton;
    private JPanel KioskPanel;


    Admin admin = new Admin();
    Payment payment = new Payment();

    public App() {

tabbedPane.setEnabledAt(1,false);

        tabbedPane.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int index = editList.getSelectedIndex();
                        controller.swapModel(index);
                    }
                }
        );


        editList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(!e.getValueIsAdjusting()) {
                            int index = editList.getSelectedIndex();
                            controller.swapModel(index);

                        }
                    }
        });

        ProdName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        controller.setModelProperty(new KeyValuePair(AbstractController.PRODUCT_NAME, ProdName.getText()));
                    }
                });

        qtyField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setModelProperty(new KeyValuePair(AbstractController.QUANTITY, qtyField.getText()));
            }
        });

        priceField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setModelProperty(new KeyValuePair(AbstractController.PRICE, priceField.getText()));
            }
        });


        this.setContentPane(mainPanel);
        
        initialise(1,1);
        barcodeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setModelProperty(new KeyValuePair(AbstractController.BARCODE,  barcodeField.getText()));
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            admin.Login(username.getText(),password.getText(),tabbedPane);
                tabbedPane.setEnabledAt(0,false);
                username.setText("");
                password.setText("");
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin.AddItem(ProdName.getText(),qtyField.getText(), priceField.getText()
                        ,barcodeField.getText());
                Main.load();

            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setEnabledAt(1,false);
                tabbedPane.setEnabledAt(0,true);

                tabbedPane.setSelectedIndex(0);
                username.setText("");
                password.setText("");



            }
        });
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           admin.RemoveItem(Integer.parseInt(barcodeField.getText()));
                Main.load();

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    admin.EditItem(ProdName.getText(),qtyField.getText(), priceField.getText()
                            ,barcodeField.getText());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Main.load();
            }
        });
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Kiosk.Scan( scanbarcode.getText(),kioskList,totalText);
            Kiosk.Removefromstock(ProdName.getText(),qtyField.getText(), priceField.getText()
                    ,scanbarcode.getText());

            }
        });
        CASHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kiosktext;
                String totallabel;
                totallabel = totalText.getText();
                kiosktext= kioskList.getText();
                Payment.cashPayment(kiosktext,totallabel);
            }
        });
        CARDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kiosktext;
                String totallabel;
                totallabel = totalText.getText();
                kiosktext= kioskList.getText();
                Payment.cardPayment(kiosktext,totallabel);
            }
        });
    }

    public void setupEditorList(String[] names) {
        editList.setListData(names);
    }

    @Override
    public void update(KeyValuePair data) {

        int selected = tabbedPane.getSelectedIndex();

        if(selected == 1) {
            switch (data.key) {
                case AbstractController.PRODUCT_NAME:
                    ProdName.setText(data.value.toString());
                    break;

                case AbstractController.QUANTITY:
                    qtyField.setText(data.value.toString());
                    break;

                case AbstractController.PRICE:
                    priceField.setText(data.value.toString());
                    break;

                case AbstractController.BARCODE:
                  barcodeField.setText(data.value.toString());
                    break;

            }
        }
    }
}
