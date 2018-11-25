/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Wicktus
 */
public class JsonUtilsTest
{

    public JsonUtilsTest()
    {
    }

    /**
     * Test of jsonArrayMerger method, of class JsonUtils.
     */
    @Test
    public void testJsonArrayMerger()
    {
        System.out.println("Tests that jsonArrayMerger creates valid json arrays");
        String[] jsonArrays = new String[2];
        jsonArrays[0] = "{\"AAPL\": {\"quote\": {\"symbol\": \"AAPL\",\"companyName\": \"Apple Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}},\"FB\": {\"quote\": {\"symbol\": \"FB\",\"companyName\": \"Facebook Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}}}";
        jsonArrays[1] = "{\"AAPL\": {\"quote\": {\"symbol\": \"AAPL\",\"companyName\": \"Apple Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}},\"FB\": {\"quote\": {\"symbol\": \"FB\",\"companyName\": \"Facebook Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}}}";
        
        String result = JsonUtils.jsonArrayMerger(jsonArrays);
        System.out.println(result);

        
        boolean valid;
        try {
            JsonParser parser = new JsonParser();
            parser.parse(result);
            
            valid = true;
        } catch (JsonSyntaxException jse) {
            System.out.println("Not a valid Json String:" + jse.getMessage());
            valid = false;
        }

        assertTrue(valid);
    }
    
    /**
     * Test of jsonArrayBuilder method, of class JsonUtils.
     */
    @Test
    public void testJsonArrayBuilder()
    {
        System.out.println("Tests that jsonArrayBuilder creates valid json arrays");
        String[] jsonArrays = new String[2];
        jsonArrays[0] = "{\"symbol\":\"AAPL\",\"companyName\":\"Apple Inc.\",\"primaryExchange\":\"Nasdaq Global Select\",\"sector\":\"Technology\",\"calculationPrice\":\"close\",\"open\":174.96,\"openTime\":1542983400377,\"close\":172.29,\"closeTime\":1542996000289,\"high\":176.595,\"low\":172.1,\"latestPrice\":172.29,\"latestSource\":\"Close\",\"latestTime\":\"November 23, 2018\",\"latestUpdate\":1542996000289,\"latestVolume\":23480687,\"iexRealtimePrice\":null,\"iexRealtimeSize\":null,\"iexLastUpdated\":null,\"delayedPrice\":172.29,\"delayedPriceTime\":1542996000289,\"extendedPrice\":172.03,\"extendedChange\":-0.26,\"extendedChangePercent\":-0.00151,\"extendedPriceTime\":1542999598487,\"previousClose\":176.78,\"change\":-4.49,\"changePercent\":-0.0254,\"iexMarketPercent\":null,\"iexVolume\":null,\"avgTotalVolume\":42416144,\"iexBidPrice\":null,\"iexBidSize\":null,\"iexAskPrice\":null,\"iexAskSize\":null,\"marketCap\":817584621420,\"peRatio\":15.62,\"week52High\":233.47,\"week52Low\":150.24,\"ytdChange\":-0.010209559724710078}";
        jsonArrays[1] = "{\"symbol\":\"AAPL\",\"companyName\":\"Apple Inc.\",\"primaryExchange\":\"Nasdaq Global Select\",\"sector\":\"Technology\",\"calculationPrice\":\"close\",\"open\":174.96,\"openTime\":1542983400377,\"close\":172.29,\"closeTime\":1542996000289,\"high\":176.595,\"low\":172.1,\"latestPrice\":172.29,\"latestSource\":\"Close\",\"latestTime\":\"November 23, 2018\",\"latestUpdate\":1542996000289,\"latestVolume\":23480687,\"iexRealtimePrice\":null,\"iexRealtimeSize\":null,\"iexLastUpdated\":null,\"delayedPrice\":172.29,\"delayedPriceTime\":1542996000289,\"extendedPrice\":172.03,\"extendedChange\":-0.26,\"extendedChangePercent\":-0.00151,\"extendedPriceTime\":1542999598487,\"previousClose\":176.78,\"change\":-4.49,\"changePercent\":-0.0254,\"iexMarketPercent\":null,\"iexVolume\":null,\"avgTotalVolume\":42416144,\"iexBidPrice\":null,\"iexBidSize\":null,\"iexAskPrice\":null,\"iexAskSize\":null,\"marketCap\":817584621420,\"peRatio\":15.62,\"week52High\":233.47,\"week52Low\":150.24,\"ytdChange\":-0.010209559724710078}";
        
        String result = JsonUtils.jsonArrayBuilder(jsonArrays);
        System.out.println(result);
        
        boolean valid;
        try {
            JsonParser parser = new JsonParser();
            parser.parse(result);
            
            valid = true;
        } catch (JsonSyntaxException jse) {
            System.out.println("Not a valid Json String:" + jse.getMessage());
            valid = false;
        }

        assertTrue(valid);
    }
}
