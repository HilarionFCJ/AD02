/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad02;

import java.io.File;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Joaquin
 */
public class AD02 {

    static class Data {

        private List<Tienda> listaTienda;
        private List<Cliente> listaCliente;

        private Data(List<Tienda> listaTienda, List<Cliente> listaCliente) {
            this.listaTienda = listaTienda;
            this.listaCliente = listaCliente;
        }

        public List<Tienda> getListaTienda() {
            return listaTienda;
        }

        public List<Cliente> getListaCliente() {
            return listaCliente;
        }

    }

    private static File archivo = new File("datos.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Scanner entrada = new Scanner(System.in);
    private static Data data;

    public static void main(String[] args) {
        //Miramos si hay un archivo y si no iniciamos uno
        if (archivo.exists() && (archivo.length() > 0)) {

            try ( InputStreamReader isr = new InputStreamReader(new FileInputStream(archivo))) {
                //Type founderListType = new TypeToken<lista>(){}.getType();  
                data = gson.fromJson(isr, new TypeToken<Data>() {
                }.getType());
                
                isr.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Archivo no existe");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error leyendo archivo");
            }
        } else {
            data = new Data(new ArrayList<>(), new ArrayList<>());
        }

        //Empezamos la interaccion con el usuario
        while (true) {
            mostrarMenu();
            int opcionSeleccionada = entrada.nextInt();
            if (opcionSeleccionada == 1) { //Tienda
                mostrarTiendas();
            } else if (opcionSeleccionada == 2) {
                System.out.println("Escribe el nombre de tienda:");
                String nombreTienda = entrada.next();
                System.out.println("Escribe la ciudad de la tienda:");
                String ciudadTienda = entrada.next();
                data.getListaTienda().add(new Tienda(nombreTienda, ciudadTienda));
                System.out.println("Tienda añadida");
                mostrarTiendas();
                pasarAJson();
            } else if (opcionSeleccionada == 3) {
                Tienda tienda = buscarTienda();
                if (tienda instanceof Tienda) {
                    data.getListaTienda().remove(tienda);
                    System.out.println("Tienda borrada");
                } else {
                    System.out.println("Tienda no encontrada");
                }
                mostrarTiendas();
                pasarAJson();
            } else if (opcionSeleccionada == 4) { //Productos
                Tienda tienda = buscarTienda();
                mostrarProductos(tienda);
            } else if (opcionSeleccionada == 5) {
                Tienda tienda = buscarTienda();
                System.out.println("Escribe el id(números) del producto:");
                int idProducto = entrada.nextInt();
                System.out.println("Escribe la descripcion del producto:");
                String descripcionProducto = entrada.next();
                System.out.println("Escribe el precio del producto:");
                int precioProducto = entrada.nextInt();
                System.out.println("Escribe la cantidad del producto:");
                int cantidadProducto = entrada.nextInt();
                tienda.getProductos().add(new Producto(idProducto, descripcionProducto, precioProducto, cantidadProducto));
                System.out.println("Producto añadido");
                mostrarProductos(tienda);
                pasarAJson();
            } else if (opcionSeleccionada == 6) {
                Tienda tienda = buscarTienda();
                System.out.println("Escribe el id (números) del producto:");
                int idProducto = entrada.nextInt();
                Producto productoABorrar = null;
                for (Producto producto : tienda.getProductos()) {
                    if (Integer.valueOf(idProducto).equals(producto.getId())) {
                        productoABorrar = producto;
                    }
                }
                if (productoABorrar instanceof Producto) {
                    tienda.getProductos().remove(productoABorrar);
                    System.out.println("Producto borrado");
                } else {
                    System.out.println("Producto no encontrado");
                }
                mostrarProductos(tienda);
                pasarAJson();
            } else if (opcionSeleccionada == 7) { //Empleados
                Tienda tienda = buscarTienda();
                mostrarEmpleados(tienda);
            } else if (opcionSeleccionada == 8) {
                Tienda tienda = buscarTienda();
                System.out.println("Escribe el nombre del empleado:");
                String nombre = entrada.next();
                System.out.println("Escribe el primer apellido del empleado:");
                String apellido1 = entrada.next();
                System.out.println("Escribe el segundo apellido del empleado:");
                String apellido2 = entrada.next();
                tienda.getEmpleados().add(new Empleado(nombre, apellido1, apellido2));
                System.out.println("Empleado añadido");
                mostrarEmpleados(tienda);
                pasarAJson();
            } else if (opcionSeleccionada == 9) {
                Tienda tienda = buscarTienda();
                System.out.println("Escribe el nombre del empleado:");
                String nombre = entrada.next();
                System.out.println("Escribe el primer apellido del empleado:");
                String apellido1 = entrada.next();
                System.out.println("Escribe el segundo apellido del empleado:");
                String apellido2 = entrada.next();
                Empleado empleadoABorrar = null;
                for (Empleado empleado : tienda.getEmpleados()) {
                    if (empleado.getNombre().equals(nombre) && empleado.getApellido1().equals(apellido1) && empleado.getApellido2().equals(apellido2)) {
                        empleadoABorrar = empleado;
                    }
                }
                if (empleadoABorrar instanceof Empleado) {
                    tienda.getEmpleados().remove(empleadoABorrar);
                    System.out.println("Empleado borrado");
                } else {
                    System.out.println("Empleado no encontrado");
                }
                mostrarEmpleados(tienda);
                pasarAJson();
            } else if (opcionSeleccionada == 10) { //Clientes
                mostrarClientes();
            } else if (opcionSeleccionada == 11) {
                System.out.println("Escribe el nombre del cliente:");
                String nombre = entrada.next();
                System.out.println("Escribe el primer apellido del cliente:");
                String apellido1 = entrada.next();
                System.out.println("Escribe el segundo apellido del cliente:");
                String apellido2 = entrada.next();
                System.out.println("Escribe el E-mail del cliente:");
                String email = entrada.next();
                data.getListaCliente().add(new Cliente(nombre, apellido1, apellido2, email));
                System.out.println("Cliente añadido");
                mostrarClientes();
                pasarAJson();
            } else if (opcionSeleccionada == 12) {
                Tienda tienda = buscarTienda();
                System.out.println("Escribe el nombre del cliente:");
                String nombre = entrada.next();
                System.out.println("Escribe el primer apellido del cliente:");
                String apellido1 = entrada.next();
                System.out.println("Escribe el segundo apellido del cliente:");
                String apellido2 = entrada.next();
                Cliente clienteABorrar = null;
                for (Cliente cliente : data.getListaCliente()) {
                    if (cliente.getNombre().equals(nombre) && cliente.getApellido1().equals(apellido1) && cliente.getApellido2().equals(apellido2)) {
                        clienteABorrar = cliente;
                    }
                }
                if (clienteABorrar instanceof Cliente) {
                    data.getListaCliente().remove(clienteABorrar);
                    System.out.println("Cliente borrado");
                } else {
                    System.out.println("Cliente no encontrado");
                }
                mostrarClientes();
                pasarAJson();
            } else if (opcionSeleccionada == 13) {
                System.out.println("Escribe el nombre del archivo:");
                try {

                    File archivoBackup = new File(entrada.next() + ".backup");

                    FileInputStream fis;
                    fis = new FileInputStream(archivo);
                    FileOutputStream fos;
                    fos = new FileOutputStream(archivoBackup);

                    int datos;

                    while ((datos = fis.read()) != -1) {
                        fos.write(datos);
                    }

                    fis.close();
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("No se encuentra el archivo");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("No se pudo escribir en el archivo");
                }
            } else if (opcionSeleccionada == 14) {
                BufferedReader br = null;
                try {
                    URL url = new URL("http://ep00.epimg.net/rss/elpais/portada.xml");
                    br = new BufferedReader(new InputStreamReader(url.openStream()));

                    XMLReader xr = XMLReaderFactory.createXMLReader();
                    TitulosXML tx = new TitulosXML();
                    xr.setContentHandler(tx);

                    xr.parse(new InputSource(br));

                    for (String titulo : tx.getTitulos()) {
                        System.out.println(titulo);
                    }
                    tx.getTitulos();

                    br.close();
                } catch (SAXException e) {
                    e.printStackTrace();
                    System.out.println("Error SAX");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println("Error de red");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error de entrada salida");
                }
            } else {
                break;
            }
        }
        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("Selecciona una opción");
        System.out.println("1. Mostrar tiendas");
        System.out.println("2. Añadir tienda");
        System.out.println("3. Borrar tienda");
        System.out.println("4. Mostrar productos de una tienda");
        System.out.println("5. Añadir producto a tienda");
        System.out.println("6. Borrar producto de tienda");
        System.out.println("7. Mostrar empleados de tienda");
        System.out.println("8. Añadir empleado a tienda");
        System.out.println("9. Borrar empleado de tienda");
        System.out.println("10. Mostrar clientes");
        System.out.println("11. Añadir cliente");
        System.out.println("12. Borrar cliente");
        System.out.println("13. Crear copia de seguridad");
        System.out.println("14. Leer periódico el país");
        System.out.println("15. Salir ");
    }

    private static void mostrarTiendas() {
        for (Tienda tienda : data.getListaTienda()) {
            System.out.println(tienda);
        }
    }

    private static Tienda buscarTienda() {
        System.out.println("Escribe el nombre de la tienda:");
        String nombreTienda = entrada.next();
        for (Tienda tienda : data.getListaTienda()) {
            if (nombreTienda.equals(tienda.getNombre())) {
                return tienda;
            }
        }
        return null;
    }

    private static void mostrarProductos(Tienda tienda) {
        for (Producto producto : tienda.getProductos()) {
            System.out.println(producto);
        }
    }

    private static void mostrarEmpleados(Tienda tienda) {
        for (Empleado empleado : tienda.getEmpleados()) {
            System.out.println(empleado);
        }
    }

    private static void mostrarClientes() {
        for (Cliente cliente : data.getListaCliente()) {
            System.out.println(cliente);
        }
    }

    private static void pasarAJson() {
        //Creamos el Json
        Type dataType = new TypeToken<Data>() {
        }.getType();
        String json = gson.toJson(data, dataType);
        try {
            //Creamos el flujo de salida
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(json);

            //Cerramos el archivo
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de entrada salida al pasar a JSON");
        }
    }

}
