package com.agrogan.tuagrogan.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by B5191 on 11/22/2015.
 */
public class Category implements Serializable{

    public  Category(){
        itemDisplay = new ArrayList<>();
    }
    public String categoria;
    public List<Item> itemDisplay;
}
