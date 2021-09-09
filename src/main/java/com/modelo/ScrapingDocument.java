package com.modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

/**
 *
 * @author camil
 */
public class ScrapingDocument {

    private String tourl;
    private double tamanopage;
    private ArrayList<String> enlcimg;
    private ArrayList<String> enlcpages;
    private ArrayList<String> tipo_enlc;
    private ArrayList<Boolean> enlcbrokens;
    private int numenlc;
    private int numlineas;
    private int tamlineas[];
    private boolean contforms;
    private boolean contlog;
    private ArrayList<Integer> codes;
    private ArrayList<String> palabras;
    private ArrayList<Integer> ocupalabras;
    private ArrayList<String> listaarch;

    public ScrapingDocument(String tourl) throws IOException {
        this.tourl = tourl;
        Document document = Jsoup.connect(tourl).get();
        this.tamanopage = tamaño(tourl);
        this.enlcpages = obtenerenlc(document);
        this.enlcbrokens = broken(enlcpages);
        this.numenlc = enlcpages.size();
        this.enlcimg = obtenerImage(document);
        this.numlineas = numlineas(document);
        this.contforms = cform(document);
        this.contlog = clog(document);
        this.ocupalabras = ocuPalabras(document);
    }

    public ScrapingDocument() {
    }
    

    /*
    @Override
    public void run(){
        try {
            Document document = Jsoup.connect(tourl).get();
            this.tamañopage = tamaño(tourl);
            this.enlcpages = obtenerenlc(document);
            this.enlcbrokens = broken(enlcpages);
            this.numenlc = enlcpages.size();
            this.enlcimg = obtenerImage(document);
            this.numlineas = numlineas(document);
            this.contforms = cform(document);
            this.contlog = clog(document);
            this.ocupalabras = ocuPalabras(document);
        } catch (IOException ex) {
            Logger.getLogger(ScrapingDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public ArrayList<String> obtenerImage(Document document) throws IOException {
        ArrayList<String> nenlcimgs = new ArrayList<String>();
        Elements imageElements = document.select("img");
        for (Element enlcElement : imageElements) {
            String strenlcURL2 = enlcElement.attr("abs:src");
            //System.out.println(strenlcURL2);
            nenlcimgs.add(strenlcURL2);
        }
        return nenlcimgs;
    }

    public ArrayList<String> obtenerenlc(Document document) throws IOException {
        //ValidadorURL VU = new ValidadorURL();
        ArrayList<String> tenlc = new ArrayList<String>();
        ArrayList<String> enlcpgs = new ArrayList<String>();
        Elements enlcElements = document.select("a[href]");
        for (Element enlcElement : enlcElements) {
            String strenlcURL = enlcElement.attr("abs:href");
            if (strenlcURL.contains("http") == true) {
                enlcpgs.add(strenlcURL);
                if (strenlcURL.contains(".pdf") == true) {
                    tenlc.add("#1");
                } else if (strenlcURL.contains(".html") == true) {
                    tenlc.add("#2");
                } else {
                    tenlc.add("#3");
                }
            }
        }
        setTipo_enlc(tenlc);
        return enlcpgs;
    }

    public boolean descargarImage(String strImageURL) {
        final String DESTINO_IMAGE = "C:\\Users\\camil\\OneDrive\\Documentos\\Universidad 2021-1\\IS\\Proyecto final IS\\web_scraping\\src\\main\\java\\imgs";
        String NombreImage = strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

        System.out.println("Guardando: " + NombreImage + ", en: " + strImageURL);

        try {
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();
            byte[] buffer = new byte[4096];
            int n = -1;
            OutputStream os = new FileOutputStream(DESTINO_IMAGE + "/" + NombreImage);
            while ((n = in.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }
            os.close();

            System.out.println("Imagen guardada.");
            return true;
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    public double tamaño(String tourl) throws MalformedURLException, IOException {
        URL url = new URL(tourl);
        HttpURLConnection HC = (HttpURLConnection) url.openConnection();
        DecimalFormat formato1 = new DecimalFormat("#,###");
        double CL = Double.parseDouble(formato1.format(HC.getContentLength() / 1024.00));
        return CL;
    }

    public ArrayList<Boolean> broken(ArrayList<String> enlcpages) throws IOException {
        ArrayList<Integer> cds = new ArrayList<Integer>();
        ArrayList<Boolean> brokens = new ArrayList<Boolean>();
        for (int i = 0; i < enlcpages.size(); i++) {
            URL url = new URL(enlcpages.get(i));
            HttpURLConnection HC = (HttpURLConnection) url.openConnection();
            if (HC.getResponseCode() >= 200 && HC.getResponseCode() <= 202) {
                cds.add(HC.getResponseCode());
                brokens.add(true);
            } else {
                cds.add(HC.getResponseCode());
                brokens.add(false);
            }
        }
        setCodes(cds);
        return brokens;
    }

    private int numlineas(Document document) {
        String tourl2 = document.outerHtml();
        String lineasv[] = tourl2.split("\n");
        setTamlineas(new int[lineasv.length]);
        for (int i = 0; i < lineasv.length; i++) {
            getTamlineas()[i] = lineasv[i].length();
        }
        return lineasv.length;
    }

    public ArrayList<String> SplitURLs(String URLs) {
        String[] URLsep = URLs.split(" ");
        System.out.println("Cantidad: "+URLsep.length);
        ArrayList<String> URLfins = new ArrayList<String>();
        for (int i = 0; i < URLsep.length; i++) {
            if (URLsep[i].trim().length() > 0) {
                URLfins.add(URLsep[i]);
            }
        }
        return URLfins;
    }

    public boolean cform(Document document) {
        String tourl2 = document.outerHtml();
        if (tourl2.contains("<form")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean clog(Document document) {
        ArrayList<String> inps = new ArrayList<String>();

        Elements inputs = document.select("input");
        for (Element element : inputs) {
            inps.add(element.attr("type").toString());

        }
        boolean existelog = inps.contains("password");
        return existelog;
    }

    public String[] NormTexto(String txt) {
        ArrayList<String> plbrs = new ArrayList<>();
        txt = txt.toLowerCase();
        txt = Normalizer.normalize(txt, Normalizer.Form.NFD);
        txt = txt.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        //System.out.println(txt);
        String vali = "";
        for (int i = 0; i < txt.length(); i++) {
            if (((int) txt.charAt(i) >= 97 && (int) txt.charAt(i) <= 122) || (int) txt.charAt(i) == 32) {
                vali += txt.charAt(i);
            } else {
                vali += " ";
            }
        }
        String vali2 = "";
        for (int i = 0; i <= vali.length(); i++) {
            if (i == vali.length()) {
                if (!vali2.equals("")) {
                    plbrs.add(vali2);
                }
                break;
            }
            if (vali.charAt(i) != ' ') {
                vali2 += vali.charAt(i);
            } else {
                if (!vali2.equals("")) {
                    plbrs.add(vali2);
                }
                vali2 = "";
            }
        }

        String palabras[] = new String[plbrs.size()];
        palabras = plbrs.toArray(palabras);

        return palabras;
    }

    private ArrayList<String> conectores() {
        ArrayList<String> cntrs = new ArrayList<String>();
        cntrs.add("a");
        cntrs.add("y");
        cntrs.add("la");
        cntrs.add("el");
        cntrs.add("de");
        cntrs.add("o");
        cntrs.add("es");
        cntrs.add("los");
        cntrs.add("las");
        cntrs.add("en");
        cntrs.add("del");
        cntrs.add("para");
        cntrs.add("pues");
        cntrs.add("por");
        cntrs.add("con");
        cntrs.add("al");
        cntrs.add("te");
        cntrs.add("e");
        cntrs.add("lo");
        cntrs.add("sobre");
        cntrs.add("ya");
        return cntrs;
    }

    private ArrayList<Integer> ocuPalabras(Document document) {
        ArrayList<String> cntrs = conectores();
        String palabras[] = NormTexto(document.text());
        ArrayList<String> plbrs = new ArrayList<>();
        ArrayList<Integer> ocuplbrs = new ArrayList<>();
        int vali = 0;
        boolean busqueda;
        for (int i = 0; i < palabras.length; i++) {
            busqueda = cntrs.contains(palabras[i]);
            if (!plbrs.contains(palabras[i])) {
                plbrs.add(palabras[i]);
                ocuplbrs.add(1);
            } else {
                vali = plbrs.indexOf(palabras[i]);
                ocuplbrs.set(vali, (ocuplbrs.get(vali) + 1));
            }
        }
        for (int j = 0; j < plbrs.size(); j++) {
            busqueda = cntrs.contains(plbrs.get(j).trim());
            if (busqueda) {
                plbrs.remove(j);
                ocuplbrs.remove(j);
            }
        }
        setPalabras(plbrs);
        return ocuplbrs;
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">>  
    public ArrayList<Integer> getOcupalabras() {
        return ocupalabras;
    }

    public void setOcupalabras(ArrayList<Integer> ocupalabras) {
        this.ocupalabras = ocupalabras;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public ArrayList<Integer> getCodes() {
        return codes;
    }

    public void setCodes(ArrayList<Integer> codes) {
        this.codes = codes;
    }

    public boolean isContforms() {
        return contforms;
    }

    public void setContforms(boolean contforms) {
        this.contforms = contforms;
    }

    public boolean isContlog() {
        return contlog;
    }

    public void setContlog(boolean contlog) {
        this.contlog = contlog;
    }

    public ArrayList<String> getTipo_enlc() {
        return tipo_enlc;
    }

    public void setTipo_enlc(ArrayList<String> tipo_enlc) {
        this.tipo_enlc = tipo_enlc;
    }

    public double getTamanopage() {
        return tamanopage;
    }

    public void setTamanopage(double tamanopage) {
        this.tamanopage = tamanopage;
    }

    public ArrayList<String> getEnlcimg() {
        return enlcimg;
    }

    public void setEnlcimg(ArrayList<String> enlcimg) {
        this.enlcimg = enlcimg;
    }

    public ArrayList<String> getEnlcpages() {
        return enlcpages;
    }

    public void setEnlcpages(ArrayList<String> enlcpages) {
        this.enlcpages = enlcpages;
    }

    public ArrayList<Boolean> getEnlcbrokens() {
        return enlcbrokens;
    }

    public void setEnlcbrokens(ArrayList<Boolean> enlcbrokens) {
        this.enlcbrokens = enlcbrokens;
    }

    public int getNumenlc() {
        return numenlc;
    }

    public void setNumenlc(int numenlc) {
        this.numenlc = numenlc;
    }

    public ArrayList<String> getListaarch() {
        return listaarch;
    }

    public void setListaarch(ArrayList<String> listaarch) {
        this.listaarch = listaarch;
    }

    public int getNumlineas() {
        return numlineas;
    }

    public void setNumlineas(int numlineas) {
        this.numlineas = numlineas;
    }

    public int[] getTamlineas() {
        return tamlineas;
    }

    public void setTamlineas(int[] tamlineas) {
        this.tamlineas = tamlineas;
    }
// </editor-fold>  
}
