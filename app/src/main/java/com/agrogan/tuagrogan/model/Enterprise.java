package com.agrogan.tuagrogan.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B5191 on 11/22/2015.
 */
public class Enterprise {

    public  Enterprise(){

        categories= new ArrayList<>();
        mainData = new ArrayList<>();
    }
    public String nombre;
    public String ubicacion;
    public String longitud;
    public String latitud;
    public String telefono;
    public String mail;
    public int image;
    public String imageUrl;
    public List<Category> categories;
    public List<Item> mainData;
}
