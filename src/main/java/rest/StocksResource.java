/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Wicktus
 */
@Path("stocks")
public class StocksResource
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StocksResource
     */
    public StocksResource()
    {
    }

    /**
     * Retrieves a stock from the IEX API
     *
     * @param symbol
     * @return a response containing JSON or an error depending on context
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @GET
    @Path("single/{symbol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStock(@PathParam("symbol") String symbol) throws InterruptedException, ExecutionException
    {
        StockFetcher sf = new StockFetcher();
        return sf.singleFetch(symbol);
    }

    /**
     * Retrieves an array of stocks from the IEX API. Query is formatted as
     * "/stocks?batch=fb&batch=amzn&batch=amd" etc.
     *
     * @param symbols
     * @return a response containing JSON or an error depending on context
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockBatch(@QueryParam("batch") List<String> symbols) throws InterruptedException, ExecutionException
    {
        StockFetcher sf = new StockFetcher();
        return sf.multiFetch(symbols);
    }

    /**
     * Retrieves an array of stocks from the IEX API by their pre-defined lists.
     *
     * @param type what type of list you want. options are "gainers, losers, iexvolume, mostactive, infocus and iexpercent"
     * @return a response containing JSON or an error depending on context
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @GET
    @Path("/list/{type}") //Gainers, losers
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockList(@PathParam("type") String type) throws InterruptedException, ExecutionException
    {
        StockFetcher sf = new StockFetcher();
        return sf.listFetch(type);
    }
}
