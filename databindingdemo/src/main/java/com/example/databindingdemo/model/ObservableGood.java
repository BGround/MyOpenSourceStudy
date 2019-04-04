package com.example.databindingdemo.model;

import android.databinding.ObservableField;

public class ObservableGood {
    private ObservableField<String> name;
    private ObservableField<String> details;

    public ObservableGood(String name,String details) {
        this.name = new ObservableField<>(name);
        this.details =new ObservableField<>(details);
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }
    public ObservableField<String> getName() {
        return name;
    }
    public void setDetails(ObservableField<String> details) {
        this.details = details;
    }
    public ObservableField<String> getDetails() {
        return details;
    }
}
