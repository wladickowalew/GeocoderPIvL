/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgeocoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class TestGeocoder {

    
    public static void main(String[] args) {
        try {
            String address = "Смоленск, Седова, 24А";
            String server = "https://geocode-maps.yandex.ru/1.x/";
            String params = "format=json" +
                    "&geocode="+URLEncoder.encode(address,"UTF-8")+
                    "&apikey="+URLEncoder.encode(getKey(),"UTF-8");
            String URL = server + "?" + params;
            getGeocoder(URL);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
    
    private static String getKey(){
        return "";
    }
    
    public static void getGeocoder(String url) throws Exception{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        InputStreamReader stream = 
                new InputStreamReader(con.getInputStream());
        BufferedReader in = new BufferedReader(stream);
        String tmp;
        StringBuffer response = new StringBuffer();
        
        while ((tmp = in.readLine()) != null){
            response.append(tmp);
        }
        in.close();
        stream.close();
        System.out.println(response.toString());
    }
    
}
