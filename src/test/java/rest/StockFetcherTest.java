/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Wicktus
 */
public class StockFetcherTest
{

    public StockFetcherTest()
    {
    }

    /**
     * Testing the multiFetch method from the StockFetcher class. Checks to see
     * if response fetch is correct by checking the response code.
     */
    @Test
    public void testMultiFetch()
    {
        System.out.println("Testing the multiFetch method from the StockFetcher class");
        List<String> symbolList = new ArrayList()
        {
            {
                add("AMD");
                add("FB");
                add("AMZN");
                add("GOOG");
                add("NVDA");
            }
        };

        StockFetcher instance = new StockFetcher();
        int expectedResponseCode = 200;
        Response result = instance.multiFetch(symbolList);
                System.out.println(result.getEntity());
        assertEquals(expectedResponseCode, result.getStatus());
    }

    /**
     * Testing the singleFetch method from the StockFetcher class. Checks to see
     * if response fetch is correct by checking the response code.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @Test
    public void testSingleFetch() throws InterruptedException, ExecutionException
    {
        System.out.println("Testing the singleFetch method from the StockFetcher class");
        String stock = "amd";

        StockFetcher instance = new StockFetcher();
        int expectedResponseCode = 200;
        Response result = instance.singleFetch(stock);

        assertEquals(expectedResponseCode, result.getStatus());
    }

    /**
     * Test of listFetch method, of class StockFetcher.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @Test
    public void testListFetch() throws InterruptedException, ExecutionException
    {
        System.out.println("Testing the listFetch method from the StockFetcher class");
        String type = "gainers";

        StockFetcher instance = new StockFetcher();
        int expectedResponseCode = 200;
        Response result = instance.listFetch(type);

        assertEquals(expectedResponseCode, result.getStatus());
    }

}
