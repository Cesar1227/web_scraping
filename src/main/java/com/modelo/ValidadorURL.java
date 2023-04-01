package com.modelo;

import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author camil
 */
public class ValidadorURL {


    private String tourl;
    private boolean estadopage;
    
    public String getTourl() {
        return tourl;
    }

    public void setTourl(String tourl) {
        this.tourl = tourl;
    }

    public boolean isEstadopage() {
        return estadopage;
    }

    public void setEstadopage(boolean estadopage) {
        this.estadopage = estadopage;
    }
    public ValidadorURL() {
    }
    
    public ValidadorURL(String tourl, boolean estadopage){
        this.tourl=tourl;
        this.estadopage=estadopage;
    }
    
 

    public static boolean urlV1(String url) throws IOException {

        /*validación de url*/
        try {
            new URL(url).openStream().close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    private static final String URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))"
            + "(%{2}|[-()_.!~*';/?:@&=+$, A-Za-z0-9])+)" + "([).!';/?:, ][[:blank:]])?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean urlV2(String url) {

        if (url == null) {
            return false;
        }

        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }

}
