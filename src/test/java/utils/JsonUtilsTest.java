/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static org.junit.Assert.assertEquals;
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
        System.out.println(expResult);

        assertEquals(expResult, result);
    }
}
