/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.bussiness.jobs;

import com.example.demo.bussiness.services.ArticuloService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ActualizarArticulos {
    
    @Value("${url.api}")
    private String url;
    
    @Autowired
    private ArticuloService articuloService;

    /**
     * initialDelay: milisegundos desde la instanciación del bean hasta la primera ejecución programada del método.
     * fixedDelay: milisegundos tras el fin de la última ejecución del método que deben pasar hasta una nueva ejecución del método. Lo usaremos para métodos que se deban ejecutar con una frecuencia muy alta y que sean susceptibles de durar bastante.
     * fixedRate: el método se ejecutará según el periodo indicado en milisegundos, sin tenerse en cuenta si la última ejecución ya terminó.
     * cron: permite definir una regla cron estableciendo un periodo regular de ejecución de forma muy precisa, por ejemplo a cierta hora de un día de la semana. El formato de la expresión cron es el mismo que el admitido por Quartz y podemos apoyarnos en este generador online.
     */
    @Scheduled(fixedDelay = 600000)
    private void consultarApi(){ 
        BufferedReader in;
        String linea;
        String jsontxt = "";
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
        try {
            URL url_base = new URL(url);
            HttpsURLConnection httpcon = (HttpsURLConnection) url_base.openConnection();
            httpcon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
            httpcon.setConnectTimeout(5000);
            httpcon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpcon.setRequestProperty("Accept", "application/json; charset=UTF-8");
            httpcon.setRequestMethod("GET");
            switch (httpcon.getResponseCode()) {
                case 200:
                    try {
                        in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
                        linea = in.readLine();
                        JSONObject hits = new JSONObject(linea);
                        JSONArray articulos = hits.getJSONArray("hits");
                        for (int i = 0; i < articulos.length(); i++) {
                            JSONObject articulo = (JSONObject) articulos.get(i);
                            this.articuloService.saveArticulo(articulo);
                        }
                    }catch (IOException ioexcep) {
                        System.out.println(ioexcep.getStackTrace());
                    }
                case 403:                  
                    System.out.println(httpcon.getErrorStream());
                case 404:
                    System.out.println(httpcon.getErrorStream());
            }
        } catch (ProtocolException ex) {
            Logger.getLogger(ActualizarArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ActualizarArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ActualizarArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
