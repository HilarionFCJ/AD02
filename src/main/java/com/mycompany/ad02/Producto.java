/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad02;

/**
 *
 * @author Joaquin
 */
class Producto {

    private final int id;
    private final String descripcion;
    private final int precio;
    private final int cantidad;

    public Producto(int id, String descripcion, int precio, int cantidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto ID: " + id + " Descripcion: " + descripcion + " Precio: " + precio + " Cantidad: " + cantidad;
    }

    public int getId() {
        return id;
    }
    
}
