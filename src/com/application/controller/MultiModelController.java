package com.application.controller;

import com.application.KeyValuePair;
import com.application.model.IModelSubject;
import com.application.view.AbstractView;

import java.lang.reflect.Method;

public class MultiModelController extends AbstractController {

    IModelSubject[] models;
    AbstractView view;

    IModelSubject currentModel;

    private Method[] modelMethods;

    public MultiModelController(IModelSubject[] models, AbstractView view) {
        this.models = models;
        this.view = view;

        currentModel = models[0];

        currentModel.add(this);
        view.setController(this);

        modelMethods = currentModel.getClass().getDeclaredMethods();

        setupModel();
    }

    @Override
    public void swapModel(int index) {

        if(index >= 0 && index < models.length) {
            if (currentModel != models[index]) {
                currentModel.remove(this);

                currentModel = models[index];
                currentModel.add(this);

                modelMethods = currentModel.getClass().getDeclaredMethods();
            }
        }

        setupModel();
    }

    private void setupModel() {
        try {
            for(Method method : modelMethods) {
                if (method.getName().equals("get" + PRODUCT_NAME)) {
                    Object value = method.invoke(currentModel);
                    updateView(new KeyValuePair(PRODUCT_NAME, (String)value));
                }
                else if (method.getName().equals("get" + QUANTITY)) {
                    Object value = method.invoke(currentModel);
                    updateView(new KeyValuePair(QUANTITY, (int)value));
                }
                else if (method.getName().equals("get" + PRICE)) {
                    Object value = method.invoke(currentModel);
                    updateView(new KeyValuePair(PRICE, (float)value));
                }
                else if (method.getName().equals("get" + BARCODE)) {
                    Object value = method.invoke(currentModel);
                    updateView(new KeyValuePair(BARCODE, (int)value));
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void setModelProperty(KeyValuePair data) {
        try {
            String methodName = "set" + data.key;
            for(Method method : modelMethods) {
                if (method.getName().equals(methodName)) {
                    method.invoke(currentModel, data.value);
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateView(KeyValuePair data) {
        view.update(data);
    }
}
