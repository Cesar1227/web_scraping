package com.servlets;

import com.google.gson.Gson;
import com.modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camil
 */
public class ValiURL extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValURL</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet ValURL at " + request.getContextPath() + "</h1>");
            // out.println("<a href='index.jsp'>Inicio</a><br>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String tourl = request.getParameter("url");
            ArrayList<ValidadorURL> urls=validarURLs(tourl);
            System.out.println("CANTIDAD DE URLS: "+urls.size()+" URLS:"+tourl);
            String jsons[]=lanzarHilos(urls.size(),urls);
            out.println(Arrays.toString(jsons));
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String tourl = request.getParameter("url");
            ArrayList<ValidadorURL> urls=validarURLs(tourl);
            System.out.println("CANTIDAD DE URLS: "+urls.size()+" URLS:"+tourl);
            String jsons[]=lanzarHilos(urls.size(),urls);
            out.println(Arrays.toString(jsons));

        }
    }

    private ArrayList<ValidadorURL> validarURLs(String tourl) throws IOException {
        ScrapingDocument SD = new ScrapingDocument();
        ArrayList<String> URLfins = SD.SplitURLs(tourl);


        ValidadorURL VU = new ValidadorURL();

        ValidadorURL VAURLS[] = new ValidadorURL[URLfins.size()];
        for (int k = 0; k < VAURLS.length; k++) {
            if (VU.urlV1(URLfins.get(k)) == true && VU.urlV2(URLfins.get(k)) == true) {

                VAURLS[k] = new ValidadorURL(URLfins.get(k), true);
            } else {
                VAURLS[k] = new ValidadorURL(URLfins.get(k), false);
            }
        }

        ArrayList<ValidadorURL> URLlist = new ArrayList<ValidadorURL>(Arrays.asList(VAURLS));

        return URLlist;
    }

    private String[] lanzarHilos(int n, ArrayList<ValidadorURL> URLlist) throws IOException {
        
        Hilos hilos[] = new Hilos[n];

        for(int j = 0; j < n; j++){
            hilos[j]=new Hilos(URLlist.get(j).getTourl(),URLlist.get(j).isEstadopage());

        }
        for(int j = 0; j < n; j++){
            hilos[j].start();
        }
        for(int j=0;j<n;j++){
            try {
                hilos[j].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ValiURL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Gson gson = new Gson();

        String json[] = new String[n];                

        
        for (int i = 0; i < n; i++) {
            json[i]=gson.toJson(hilos[i].getScraping());            
        }
        return json;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
