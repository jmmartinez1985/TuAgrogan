package com.agrogan.tuagrogan;

import android.app.Application;

import com.agrogan.tuagrogan.model.Enterprise;

/**
 * Created by B5191 on 11/22/2015.
 */
public class ApplicationObject extends Application {

    private Enterprise enterprise;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
