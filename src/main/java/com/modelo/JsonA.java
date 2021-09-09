/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cesar Bonilla
 */
public class JsonA {
    
    double tamañoKb;
    int nLineas;
    ArrayList<Double> tamañoLinea;
    ArrayList<String> palabra;
    ArrayList<Integer> ocuPalabra;
    int nEnlaces;
    ArrayList<String> tipoEnlace;
    ArrayList<String> enlaces;
    ArrayList<Boolean> estadoEnlace;
    ArrayList<String> imagenes;
    boolean logueo;
    boolean capturaDatos;
    ArrayList<Object> archivos;
    public StringBuffer json= null;

    public JsonA(double tamañoKb, int nLineas, ArrayList<Double> tamañoLinea, ArrayList<String> palabra, ArrayList<Integer> ocuPalabra) {
        this.tamañoKb = tamañoKb;
        this.nLineas = nLineas;
        this.tamañoLinea = tamañoLinea;
        this.palabra = palabra;
        this.ocuPalabra = ocuPalabra;
    }

    public JsonA() {
        this.llenar();
    }
    
    public JsonA(double tamañoKb, int nLineas, ArrayList<Double> tamañoLinea, ArrayList<String> palabra, ArrayList<Integer> ocuPalabra,int nEnlaces ,ArrayList<String> enlaces,ArrayList<Boolean> estadoEnlace, ArrayList<String> imagenes, boolean logueo, boolean capturaDatos, ArrayList<Object> archivos) {
        this.tamañoKb = tamañoKb;
        this.nLineas = nLineas;
        this.tamañoLinea = tamañoLinea;
        this.palabra = palabra;
        this.ocuPalabra = ocuPalabra;
        this.nEnlaces= nEnlaces;
        this.enlaces = enlaces;
        this.estadoEnlace=estadoEnlace;
        this.imagenes = imagenes;
        this.logueo = logueo;
        this.capturaDatos = capturaDatos;
        this.archivos = archivos;
    }
    
    public void generarJSON(){
        json= new StringBuffer("{\"data\":{");
        json.append("\"tamaño\":\"").append(tamañoKb).append("\",");
        json.append("\"nLineas\":\"").append(nLineas).append("\",");
        json.append("\"tamañoLineas\":\"").append(tamañoLinea.toString()).append("\",");
        json.append("\"ocurrencia\":\"").append(palabra.toString()).append("\",");
        json.append("\"nEnlace\":\"").append(nEnlaces).append("\",");
        json.append("\"enlaces\":\"").append(enlaces.toString()).append("\",");
        json.append("\"estadoEnlace\":\"").append(estadoEnlace.toString()).append("\",");
        json.append("\"imagenes\":\"").append(imagenes).append("\",");
        json.append("\"logueo\":\"").append(logueo).append("\",");
        json.append("\"capturaDatos\":\"").append(capturaDatos).append("\",");
        json.append("\"archivos\":\"").append(archivos.toString()).append("\"");
        json.append("}}");        
    }
    
    private void llenar(){
        this.tamañoKb=5.0;
        this.nLineas=15;
        tamañoLinea = new ArrayList<Double>();
        tamañoLinea.add(Double.valueOf(1));
        tamañoLinea.add(Double.valueOf(2));
        tamañoLinea.add(Double.valueOf(3));
        
        palabra = new ArrayList<String>();
        palabra.add("1");
        palabra.add("2");
        palabra.add("3");
        
        ocuPalabra = new ArrayList<Integer>();
        ocuPalabra.add(2);
        ocuPalabra.add(3);
        ocuPalabra.add(4);
        
        this.nEnlaces=4;
        enlaces= new ArrayList<String>();
        enlaces.add("a");
        enlaces.add("b");
        enlaces.add("c");
        
        estadoEnlace = new ArrayList<Boolean>();
        estadoEnlace.add(true);
        estadoEnlace.add(true);
        estadoEnlace.add(false);
        
        imagenes= new ArrayList<String>();
        imagenes.add(new String());
        imagenes.add(new String());
        imagenes.add(new String());
        
        this.logueo=false;
        this.capturaDatos=true;
        
        archivos = new ArrayList<Object>();
        archivos.add(new Object());
        archivos.add(new Object());
        archivos.add(new Object());
        
        //Json obj = new Json(10.0,5,listTam,listPal,listEnlaces);
        this.generarJSON();
    }
        
    public static void main(String []args){
        
        List<Double> listTam = new ArrayList<Double>();
        listTam.add(Double.valueOf(1));
        listTam.add(Double.valueOf(2));
        listTam.add(Double.valueOf(3));
        
        List<String> listTam1 = new ArrayList<String>();
        listTam1.add("1");
        listTam1.add("2");
        listTam1.add("3");
        
        List<Integer> listTam2 = new ArrayList<Integer>();
        listTam2.add(1);
        listTam2.add(2);
        listTam2.add(3);
        
        /*JsonA obj = new JsonA(10.0,5,listTam,listTam1,listTam2);
        obj.generarJSON();
        System.out.println(obj.json);    */       
    }
}
