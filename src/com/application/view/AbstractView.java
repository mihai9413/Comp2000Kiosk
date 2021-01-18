package com.application.view;

import com.application.KeyValuePair;
import com.application.controller.AbstractController;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractView extends JFrame {
    protected AbstractController controller;

    protected void initialise(int rows, int cols) {
        this.setLayout(new GridLayout(rows,cols));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        this.pack();

        this.setVisible(true);
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public abstract void update(KeyValuePair data);

    protected float doubleToFloat(double value) {
        return (float)value;
    }

    protected double floatToDouble(float value) {
        return (double)value;
    }

}
