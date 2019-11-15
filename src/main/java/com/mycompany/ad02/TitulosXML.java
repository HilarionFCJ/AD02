package com.mycompany.ad02;


import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joaquin
 */
public class TitulosXML extends DefaultHandler {

    //Aqui imos gardar os datos de todalas persoas do XML
    private ArrayList<String> titulos = new ArrayList<>();
    private String titulo;
    boolean enNoticias;

    public TitulosXML() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        enNoticias = false;
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("item".equals(localName)) {
            enNoticias = true;

        } else if ("title".equals(localName) && enNoticias) {
            this.titulo = titulo = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("item".equals(localName)) {
            enNoticias = false;

        } else if ("title".equals(localName) && enNoticias) {
            this.titulos.add(this.titulo);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //Gardamos todo o texto entre caracteres na cadea de texto auxiliar
        this.titulo = new String(ch, start, length);
    }

    public ArrayList<String> getTitulos() {
        return titulos;
    }
}
