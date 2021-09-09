/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import com.database.Documento;
import com.google.gson.Gson;
import com.modelo.ScrapingDocument;
import java.io.IOException;

/**
 *
 * @author Cesar Bonilla
 */
public class Json {

    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();

        String json = gson.toJson(new ScrapingDocument("https://google.com"));
        System.out.println(json);
    }
}
