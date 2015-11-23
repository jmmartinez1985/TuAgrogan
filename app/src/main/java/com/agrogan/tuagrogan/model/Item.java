package com.agrogan.tuagrogan.model;

/**
 * Created by B5191 on 11/22/2015.
 */
public class Item {

    public String code;
    public String descripcion;
    public String descripcionLarga;
    public String origen;
    public int image;
    public String imageUrl;


    public Item(){}
    public Item(String code, String descripcion, String descripcionLarga, String origen, int image, String imageUrl) {
        this.code = code;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.origen = origen;
        this.image = image;
        this.imageUrl = imageUrl;
    }

    public Item(String code, String descripcion, String descripcionLarga, String origen, int image) {
        this.code = code;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.origen = origen;
        this.image = image;
    }

}
