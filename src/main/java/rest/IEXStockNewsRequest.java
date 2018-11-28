/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import javax.ws.rs.core.Response;

/**
 *
 * @author martin
 */
public class IEXStockNewsRequest implements Callable {
    private String symbol;
    
    public IEXStockNewsRequest(String symbol){
        this.symbol = symbol;
    }

    @Override
    public Object call() throws Exception {
        return Response.ok(getIEXStockNews(symbol)).build();

    }

    private String getIEXStockNews(String symbol) throws MalformedURLException, IOException {
        URL url = new URL("https://api.iextrading.com/1.0/stock/" + symbol + "/news");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        String jsonStr;
        try (Scanner scan = new Scanner(con.getInputStream())) {
            jsonStr = null;
            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
        }
        return jsonStr;
    }

}
