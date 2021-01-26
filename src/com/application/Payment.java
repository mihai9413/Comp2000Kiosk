package com.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Payment {

    public static void cashPayment(String kiosklist,String totallabb){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

                JFrame payByCash = new JFrame("Pay by CASH");
                payByCash.setSize(500,400);
                payByCash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                payByCash.setLayout(new GridLayout(3,1,1,5));
                payByCash.setVisible(true);
                JTextField cashamo = new JTextField();
                JLabel totallabel =  new JLabel();
                JLabel changelabel = new JLabel();
                JButton button = new JButton("PAY");


                payByCash.add(button);

                payByCash.add(totallabel);
                payByCash.add(cashamo);
                payByCash.add(changelabel);

        totallabel.setText("TOTAL: " + totallabb);
        changelabel.setText("Insert Cash Amount");
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        float total = Float.parseFloat(cashamo.getText())-Float.parseFloat(totallabb);

        if (Float.parseFloat(cashamo.getText()) > Float.parseFloat(totallabb)){

            totallabel.setText("TOTAL: " + totallabb);

            Frame rec = new JFrame("Receipt");
            rec.setSize(200,200);
            rec.setVisible(true);
            JTextArea receipt = new JTextArea();
            rec.add(receipt);

            receipt.setText(kiosklist + "\n" + "Total: £" + totallabb  + "\n" + "Change: £" + total  + "\n" + (dtf.format(now)) + "\n");
            payByCash.dispose();

        }
        else if(Float.parseFloat(cashamo.getText()) == Float.parseFloat(totallabb))   {
            totallabel.setText("TOTAL: " + totallabb);
            Frame rec = new JFrame("Receipt");
            rec.setSize(200,200);
            rec.setVisible(true);
            JTextArea receipt = new JTextArea();
            rec.add(receipt);
            receipt.setText(kiosklist + "\n" + "Total: £" + totallabb  + "\n" + "Change: £" + total  + "\n" + (dtf.format(now)) + "\n");
            payByCash.dispose();
        }
        else {
            payByCash.dispose();
            JOptionPane.showMessageDialog(null,
                    "Not Enough Money", "Error",
                    JOptionPane.INFORMATION_MESSAGE);

        }


    }
});
    }
    public static void cardPayment(String kiosklist,String totallabb){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        JFrame payByCard = new JFrame("Pay by CARD");
        payByCard.setSize(500,400);
        payByCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        payByCard.setLayout(new GridLayout(4,1,1,1));
        payByCard.setVisible(true);
        JTextField pinField = new JTextField();
        JButton  payButton = new JButton("PAY");
        JButton cancelButton = new JButton("CANCEL");
        JLabel totallabel = new JLabel();

        payByCard.add(pinField);
        payByCard.add(totallabel);
        payByCard.add(payButton);
        payByCard.add(cancelButton);

        String cardPin="0000";

        totallabel.setText("Total: " + totallabb);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (pinField.getText().equals(cardPin)){
                   payByCard.dispose();
                    JOptionPane.showMessageDialog(null,
                            "Card Accepted", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);

                    Frame rec = new JFrame("Receipt");
                    rec.setSize(200,200);
                    rec.setVisible(true);
                    JTextArea receipt = new JTextArea();
                    rec.add(receipt);

                    receipt.setText(kiosklist + "\n" + "Total: £" + totallabb  + "\n" + "Change: Payed by Card " + "\n" + (dtf.format(now)) + "\n");

                }
                else {
                    payByCard.dispose();
                    JOptionPane.showMessageDialog(null,
                            "Incorrect Pin", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
cancelButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        payByCard.dispose();
    }
});
    }

}
