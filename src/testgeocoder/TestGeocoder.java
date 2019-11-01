
package testgeocoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestGeocoder {
    
    public static void main(String[] args){
        try {
            String address = "Смоленск, Седова, 24А";
            String server = "https://geocode-maps.yandex.ru/1.x/";
            String params = "format=json" +
                    "&geocode="+URLEncoder.encode(address,"UTF-8")+
                    "&apikey="+URLEncoder.encode(getKey(),"UTF-8");
            String URL = server + "?" + params;
            System.out.println("URL : " + URL);
            System.out.println("JSON: " + getGeocoder(URL));
        } catch (Exception ex) {
            Logger.getLogger(TestGeocoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public static String getURL(String address){
        
    }
    
    private static String getKey(){
        return "a880cf10-7c51-4526-9e32-517f6e45c777";
    }
    
    public static String getGeocoder(String url) throws Exception{
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
        return response.toString();
    }
    
}