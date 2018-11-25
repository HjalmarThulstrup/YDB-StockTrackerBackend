package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import java.util.Map;

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
    public static String jsonBatchMerger(String[] jsonArrays)
    {
        JsonParser parser = new JsonParser();

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < jsonArrays.length; i++) {

            JsonElement objects = parser.parse(jsonArrays[i]);
            JsonObject jsonObj = objects.getAsJsonObject();

            JsonArray arr = new JsonArray();
            for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
                //System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
                sb.append(entry.getValue().getAsJsonObject().get("quote"));
                sb.append(",");
            }
            
        }

        return sb.toString().substring(0, sb.lastIndexOf(",")) + "]";
    }

    /**
     * Takes a list of json arrays and merges them into one string.
     *
     * @param jsonArrays
     * @return single string containing a json array.
     */
    public static String jsonArrayMerger(List<String> jsonArrays)
    {
        return jsonBatchMerger(jsonArrays.toArray(new String[0]));
    }
}
