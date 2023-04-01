/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class persistencia {

    private boolean leerArchivo() throws FileNotFoundException {
        String url[], json[];
        FileReader fr = null;
        String linea = "";
        String[] datos;
        int cont = 0;
        InputStream flujoInput = ClassLoader.getSystemResourceAsStream("com/database/jsons.txt");
        
        System.out.println(ClassLoader.getSystemResource("jsons.txt").toString());
        File archivo = new File(ClassLoader.getSystemResource("jsons.txt").toString());

        System.out.println(archivo.getAbsolutePath() + " ruta del archivo----------------- ");
        fr = new FileReader(archivo);
        /*try {

            BufferedReader br = new BufferedReader(fr);
            linea = br.readLine();
            while (linea != null) {
                cont++;
                linea = br.readLine();
            }
            url = new String[cont];
            json = new String[cont];
            
            fr.close();
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            linea = br.readLine();
            int i = 0;
            while (linea != null) {
                datos = linea.split(";");
                url[i] = datos[0];
                json[i] = datos[1];
                linea = br.readLine();
                i++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(persistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(persistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(persistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        return true;
    }

    public static void main(String[] args) {
        persistencia obj = new persistencia();
        try {
            obj.leerArchivo();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
