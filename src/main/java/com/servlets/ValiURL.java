package com.servlets;

import com.google.gson.Gson;
import com.modelo.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            String tourl = request.getParameter("url");
            out.println("Sitio web: <br>" + tourl + "<br>");
            ScrapingDocument SD = new ScrapingDocument(tourl);
            ArrayList<String> enlcpages = SD.getEnlcpages();
            ArrayList<Boolean> brokens = SD.getEnlcbrokens();
            ArrayList<String> nenlcimgs = SD.getEnlcimg();
            ArrayList<String> tenlc = SD.getTipo_enlc();
            int numlineas = SD.getNumlineas();
            int lineas_size[] = SD.getTamlineas();
            int numenlc = enlcpages.size();
            if (SD.isContforms() == true) {
                out.println("<br>El sitio contiene formularios<br>");
            } else {
                out.println("<br>El sitio NO contiene formularios<br>");
            }
            //out.println(SD.isContlog());
            boolean existelog = SD.isContlog();
            if (existelog) {
                out.println("<br>El sitio contiene opción de logueo<br>");
            } else {
                out.println("<br>El sitio NO contiene opción de logueo<br>");
            }
            /*if(SD.clog()==true){
                out.println("<br>El sitio contiene opción de logueo<br>");
                }else{
                    out.println("<br>El sitio NO contiene opción de logueo<br>");
                }*/

            out.println("<br>Enlaces: <br>");
            for (int i = 0; i < enlcpages.size(); i++) {
                if (brokens.get(i) == true) {
                    out.println("Enlace #" + (i + 1) + ": " + enlcpages.get(i) + " - Tipo: " + tenlc.get(i) + " - ESTADO: ACTIVO - Code: " + SD.getCodes().get(i) + " <br>");
                } else {
                    out.println("Enlace #" + (i + 1) + ": " + enlcpages.get(i) + " - Tipo: " + tenlc.get(i) + " - ESTADO: ROTO o PROHIBIDO - Code: " + SD.getCodes().get(i) + " <br>");
                }
            }
            out.println("<br>La cantidad de enlaces es: " + SD.getNumenlc() + " <br><br>");
            out.println("<br>Imágenes - Descarga: <br>");
            for (int j = 0; j < nenlcimgs.size(); j++) {
                out.println("Enlace a imagen #" + (j + 1) + ": " + nenlcimgs.get(j) + " <br> ");
            }
            //out.println(lineas_size.length);
            for (int k = 0; k < lineas_size.length; k++) {
                out.println("Linea #" + (k + 1) + " - Cantidad de lineas: " + lineas_size[k] + "<br>");
            }

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
            /*out.println("<a href='index.jsp'>Inicio</a><br>");
            String tourl = request.getParameter("url");

            //out.println("URL: "+tourl);
            ValidadorURL vurl = new ValidadorURL();
            boolean estado = vurl.urlV1(tourl);
            boolean estado2 = vurl.urlV2(tourl);
            // out.println(estado);
            if (estado == true || estado2 == true) {
                ScrapingDocument SD = new ScrapingDocument(tourl);
                out.println("hola");
                double tamaño = SD.getTamanopage();
                out.println("El tamaño del sitio web es: " + tamaño + " KBs <br><br>");
                processRequest(request, response);
            } else {
                out.println("URL no valida.");
                //out.println("<a href='index.jsp'>Inicio</a><br>");
            }*/
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
            /*ScrapingDocument SD = new ScrapingDocument(tourl);
            ArrayList<String> URLfins = SD.SplitURLs(tourl);

            //  out.println(tourl);
            ValidadorURL vurl = new ValidadorURL();
            boolean estado = vurl.urlV1(tourl);
            if (estado == true) {
                processRequest(request, response);
            } else {
                out.println("URL no valida.");
            }*/
        }
    }

    private ArrayList<ValidadorURL> validarURLs(String tourl) throws IOException {
        ScrapingDocument SD = new ScrapingDocument();
        ArrayList<String> URLfins = SD.SplitURLs(tourl);

        /*for (int j = 0; j < URLfins.size(); j++) {
                    out.println(URLfins.get(j));
                }*/
        ValidadorURL VU = new ValidadorURL();

        ValidadorURL VAURLS[] = new ValidadorURL[URLfins.size()];
        for (int k = 0; k < VAURLS.length; k++) {
            if (VU.urlV1(URLfins.get(k)) == true || VU.urlV2(URLfins.get(k)) == true) {
                VAURLS[k] = new ValidadorURL(URLfins.get(k), true);
            } else {
                VAURLS[k] = new ValidadorURL(URLfins.get(k), false);
            }
        }

        ArrayList<ValidadorURL> URLlist = new ArrayList<ValidadorURL>(Arrays.asList(VAURLS));

        /*for (int j = 0; j < URLlist.size(); j++) {
            out.println(URLlist.get(j).getTourl());
            out.println(URLlist.get(j).isEstadopage());
        }*/
        return URLlist;
    }

    private String[] lanzarHilos(int n, ArrayList<ValidadorURL> URLlist) throws IOException {
        
        Hilos hilos[] = new Hilos[n];
        
        for(int j = 0; j < n; j++){
            hilos[j]=new Hilos(URLlist.get(j).getTourl());
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
        System.out.println("JSONS:" +Arrays.toString(json));
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
