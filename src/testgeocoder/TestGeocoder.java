
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestGeocoder {
    
    public static void main(String[] args){
        try {
            String address = "Улица ленина";
            String URL = getURL(address);
            System.out.println("URL : " + URL);
            String JSONString = getGeocoder(URL);
            System.out.println("JSON: " + JSONString);
            parseJSON(JSONString);
        } catch (Exception ex) {
            Logger.getLogger(TestGeocoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void parseJSON(String jsonString) throws JSONException{
        JSONObject obj = new JSONObject(jsonString);
        JSONObject res = obj.getJSONObject("response");
        JSONObject GOC = res.getJSONObject("GeoObjectCollection");
        JSONArray jarray = GOC.getJSONArray("featureMember");
        for (int i = 0; i < jarray.length();i++){
            JSONObject element = jarray.getJSONObject(i);
            JSONObject geoObj = element.getJSONObject("GeoObject");
            String name = geoObj.getString("name");
            String descr = geoObj.getString("description");
            JSONObject point = geoObj.getJSONObject("Point");
            String pos = point.getString("pos");
            String line = name+" "+descr
                                    +" coords: "+pos;
            System.out.println(line);
        }
    }
     
    public static String getURL(String address) throws UnsupportedEncodingException{
        String server = "https://geocode-maps.yandex.ru/1.x/";
        String params = "format=json" +
                        "&results=100" +
                    "&geocode="+URLEncoder.encode(address,"UTF-8")+
                    "&apikey="+URLEncoder.encode(getKey(),"UTF-8");
        return server + "?" + params;
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