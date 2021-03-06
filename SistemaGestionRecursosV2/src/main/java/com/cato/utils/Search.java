package com.cato.utils;

/**
 *
 * @author Alicia C
 */
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.jayway.jsonpath.JsonPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.util.Properties;

/** Example of Java client calling Knowledge Graph Search API */
public class Search {
    
  String API_KEY = "AIzaSyB71yL3Jv5dvIROXKNcoy0_mvck0GZIiCM";  
  public static Properties properties = new Properties();
  public static void main(String[] args) {
    
    try {
        
    //  properties.load(new FileInputStream("kgsearch.properties"));

      HttpTransport httpTransport = new NetHttpTransport();
      HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
      JSONParser parser = new JSONParser();
      GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
      url.put("query", "Taylor Swift");
      url.put("limit", "10");
      url.put("indent", "true");
      url.put("key", "AIzaSyB71yL3Jv5dvIROXKNcoy0_mvck0GZIiCM");//properties.get("API_KEY"));
      HttpRequest request = requestFactory.buildGetRequest(url);
      HttpResponse httpResponse = request.execute();
      JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
      JSONArray elements = (JSONArray) response.get("itemListElement");
      for (Object element : elements) {
        System.out.println(JsonPath.read(element, "$.result.name").toString());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  public JSONObject BuscarRecursosOnline(String tema){
      
      JSONObject response = new JSONObject();
      
    try {
        
      HttpTransport httpTransport = new NetHttpTransport();
      HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
      JSONParser parser = new JSONParser();
      GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
      url.put("query", tema);
      url.put("limit", "10");
      url.put("indent", "true");
      url.put("key", "AIzaSyB71yL3Jv5dvIROXKNcoy0_mvck0GZIiCM");//properties.get("API_KEY"));
      HttpRequest request = requestFactory.buildGetRequest(url);
      HttpResponse httpResponse = request.execute();
      
      response = (JSONObject) parser.parse(httpResponse.parseAsString());
      
      JSONArray elements = (JSONArray) response.get("itemListElement");
      
      System.out.println(":: Elemento JSON  ::::::::: "+elements);
      
      for (Object element : elements) {
        System.out.println(JsonPath.read(element, "$.result.name").toString());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }      
      return response;
  }
}