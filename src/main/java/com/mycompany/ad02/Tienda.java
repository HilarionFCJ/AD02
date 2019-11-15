/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad02;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joaquin
 */
public class Tienda {

    private final String nombre;
    private final String ciudad;
    private final List<Producto> productos;
    private final List<Empleado> empleados;

    public Tienda(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        productos = new ArrayList<>();
        empleados = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Tienda Nombre: " + nombre + " Ciudad: " + ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

}
