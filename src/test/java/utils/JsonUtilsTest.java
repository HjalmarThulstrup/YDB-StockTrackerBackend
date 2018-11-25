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
     * Test of jsonBatchMerger method, of class JsonUtils.
     */
    @Test
    public void testJsonArrayMerger()
    {
        System.out.println("Tests that jsonArrayMerger creates valid json arrays");
        String[] jsonArrays = new String[2];
        jsonArrays[0] = "{\"AAPL\": {\"quote\": {\"symbol\": \"AAPL\",\"companyName\": \"Apple Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}},\"FB\": {\"quote\": {\"symbol\": \"FB\",\"companyName\": \"Facebook Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}}}";
        jsonArrays[1] = "{\"AAPL\": {\"quote\": {\"symbol\": \"AAPL\",\"companyName\": \"Apple Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}},\"FB\": {\"quote\": {\"symbol\": \"FB\",\"companyName\": \"Facebook Inc.\",\"primaryExchange\": \"Nasdaq Global Select\",\"sector\": \"Technology\"}}}";
        
        String result = JsonUtils.jsonBatchMerger(jsonArrays);
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
