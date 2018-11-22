package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Callable class that helps multi-thread batched fetch requests to the API.
 */
public class IEXStockBatchRequest implements Callable
{

    private final String[] symbols;
     
    protected IEXStockBatchRequest(String[] symbols)
    {
        this.symbols = symbols;
    }
    
    @Override
    public String call() throws Exception
    {
        return getIEXStockBatch(symbols);
    }

    /**
     * Fetches an arbitrarily sized batch of stocks from the API (max 100).
     * @param symbols
     * @return a JSON array of stocks.
     * @throws MalformedURLException
     * @throws IOException 
     */
    private String getIEXStockBatch(String[] symbols) throws MalformedURLException, IOException
    {
        StringBuilder sb = new StringBuilder("https://api.iextrading.com/1.0/stock/market/batch?symbols=");

        for (String symbol : symbols) {
            sb.append(symbol).append(",");
        }

        sb.append("&types=quote");

        URL url = new URL(sb.toString());
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
