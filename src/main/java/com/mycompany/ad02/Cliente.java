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
public class Cliente {

    String nombre;
    String apellido1;
    String apellido2;
    String email;

    public Cliente(String nombre, String apellido1, String apellido2, String email) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
    }

    

    @Override
    public String toString() {
        return "Cliente Nombre: " + nombre + " Primer apellido: " + apellido1 + " Segundo apellido: " + apellido2 + " E-mail: " + email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }
    
}
