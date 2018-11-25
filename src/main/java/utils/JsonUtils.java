package utils;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A set of tools for working with Json data.
 */
public class JsonUtils
{

    /**
     * Takes an array of json arrays and merges them into one string.
     *
     * @param jsonArrays
     * @return single string containing a json array.
     */
    public static String jsonArrayMerger(String[] jsonArrays)
    {
        JSONArray result = new JSONArray();

        for (String jsonArray : jsonArrays) {
            JSONObject workingObj = new JSONObject(jsonArray);

            for (int i = 0; i < workingObj.length(); i++) {
                result.put(workingObj.toJSONArray(result).get(i));
            }
        }

        return result.toString();
    }

    /**
     * Takes an array of json arrays and merges them into one string.
     *
     * @param jsonStocks
     * @return single string containing a json array.
     */
    public static String jsonArrayBuilder(String[] jsonStocks)
    {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < jsonStocks.length; i++) {
            String jsonStock = jsonStocks[i];
            sb.append(jsonStock);

            if (i < jsonStocks.length - 1) {
                sb.append(",");
            }
        }

        return sb.append("]").toString();
    }

    /**
     * Takes a list of json arrays and merges them into one string.
     *
     * @param jsonArrays
     * @return single string containing a json array.
     */
    public static String jsonArrayMerger(List<String> jsonArrays)
    {
        return jsonArrayMerger(jsonArrays.toArray(new String[0]));
    }
}
