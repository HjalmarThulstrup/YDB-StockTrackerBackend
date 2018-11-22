package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import javax.ws.rs.core.Response;

/**
 * Used to fetch a single stock from the API
 */
public class IEXStockListRequest implements Callable
{

    private final String type;

    public IEXStockListRequest(String type)
    {
        this.type = type;
    }

    @Override
    public Response call() throws Exception
    {
        return Response.ok(getIEXSingleStock(type)).build();
    }

    /**
     * Fetches a single stock from the API by symbol
     * @param symbol
     * @return a json String containing the quote of one stock.
     * @throws MalformedURLException
     * @throws IOException 
     */
    private String getIEXSingleStock(String type) throws MalformedURLException, IOException
    {
        URL url = new URL("https://api.iextrading.com/1.0/stock/market/list/" + type);
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
