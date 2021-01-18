package com.application.model;

import com.application.KeyValuePair;
import com.application.controller.AbstractController;

public interface IModelSubject {
    void add(AbstractController observer);
    void remove(AbstractController observer);
    void onPropertyChanged(KeyValuePair data);
}
