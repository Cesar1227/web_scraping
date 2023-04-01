/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class Hilos extends Thread{
    
    private ScrapingDocument scraping;
    private String url;
    private boolean val;

    public Hilos(String url,boolean valido) {
        this.url=url;
        this.val=valido;
    }
    
    @Override
    public void run(){
        try {
            if(val){
                this.scraping = new ScrapingDocument(url);
            }else{
                this.scraping = new ScrapingDocument(url,val);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ScrapingDocument getScraping() {
        return scraping;
    }

    public void setScraping(ScrapingDocument scraping) {
        this.scraping = scraping;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
