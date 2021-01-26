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

                JFrame frame = new JFrame("Pay by CASH");
                frame.setSize(500,400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new GridLayout(3,1,1,5));
                frame.setVisible(true);
                JTextArea textArea = new JTextArea();
                JTextField cashamo = new JTextField();
                JLabel totallabel =  new JLabel();
                JLabel changelabel = new JLabel();
                JButton button = new JButton("PAY");


                frame.add(button);
                frame.add(textArea);
                frame.add(totallabel);
                frame.add(cashamo);
                frame.add(changelabel);

        textArea.setText(kiosklist);
        totallabel.setText("TOTAL: " + totallabb);
        changelabel.setText("Change due: ");
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
float total = Float.parseFloat(cashamo.getText())-Float.parseFloat(totallabb);
        changelabel.setText("Change due: "+ total);
        totallabel.setText("TOTAL: " + totallabb);

        Frame rec = new JFrame("Receipt");
        rec.setSize(200,200);
        rec.setVisible(true);
        JTextArea receipt = new JTextArea();
        rec.add(receipt);

receipt.setText(kiosklist + "\n" + "Total: £" + totallabb  + "\n" + "Change: £" + total  + "\n" + (dtf.format(now)) + "\n");

    }
});
    }

}
