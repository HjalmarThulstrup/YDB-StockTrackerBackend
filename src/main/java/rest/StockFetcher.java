package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import utils.JsonUtils;

/**
 * This class is used whenever a stock needs to be fetched from the API.
 */
public class StockFetcher
{

    private final ExecutorService pool;

    public StockFetcher()
    {
        this.pool = Executors.newCachedThreadPool();
    }

    /**
     * A batch fetch with multi-threading
     *
     * @param symbolList
     * @return a response containing the collected results of multiple fetch
     * requests.
     */
    public Response multiBatchFetch(List symbolList) //only gets 3 at a time for each batch for now. Might be good to make it smarter by having it calculate some sort of number based on the size of the fetch or maybe pagination page size.
    {
        try {
            List<Future> fetchFutures = getBatchFutures(symbolList);
            return Response.ok(mergeFutureResults(fetchFutures)).build();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(StockFetcher.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    /**
     * Merges the results of the futures into a single Json array.
     *
     * @param futureList
     * @return a String representing a json array of stocks.
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private String mergeFutureResults(List<Future> futureList) throws InterruptedException, ExecutionException
    {
        List<String> futureResults = new ArrayList();

        for (Future future : futureList) {
            futureResults.add((String) future.get());
        }

        return JsonUtils.jsonArrayMerger(futureResults);
    }

    /**
     * creates mini-batches of stock symbols and adds them to a pool for
     * fetching.
     *
     * @param symbolList
     * @return a list of futures
     */
    private List<Future> getBatchFutures(List symbolList) //only gets 3 at a time for each batch for now. Might be good to make it smarter by having it calculate some sort of number based on the size of the fetch or maybe pagination page size.
    {
        List<Future> fetchFutures = new ArrayList();
        int originalSize = symbolList.size();
        int currentSize = originalSize;
        boolean batched = false;
        while (!batched) {
            int batchSize;

            if (currentSize - 3 >= 0) {
                batchSize = 3;
            } else {
                batchSize = currentSize;
                batched = true;
            }

            String[] batchArr = new String[batchSize];

            for (int i = 0; i < batchArr.length; i++) {
                batchArr[i] = symbolList.remove(0).toString();
            }

            fetchFutures.add(pool.submit(new IEXStockBatchRequest(batchArr)));
            currentSize = symbolList.size();
        }
        return fetchFutures;
    }

    /**
     * used to multithread the fetching of a single stock. Sort of pointless
     * since there is only one fetch call.
     *
     * @param symbol
     * @return a response containing a single stock
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public Response singleFetch(String symbol) throws InterruptedException, ExecutionException
    {
        return (Response) pool.submit(new IEXSingleStockRequest(symbol)).get();
    }

    /**
     * Fetches a list of stocks based on the IEX /list endpoint.
     *
     * @param type
     * @return a response containing 10 stocks.
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public Response listFetch(String type) throws InterruptedException, ExecutionException
    {
        return (Response) pool.submit(new IEXStockListRequest(type)).get();
    }
}
