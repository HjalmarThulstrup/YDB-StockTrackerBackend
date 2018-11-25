package utils;

import java.util.List;
import net.minidev.json.JSONArray;

/**
 * A set of tools for working with Json data.
 */
public class JsonUtils {

    /**
     * Takes an array of json arrays and merges them into one string.
     *
     * @param jsonArrays
     * @return single string containing a json array.
     */
    public static String jsonArrayMerger(String[] jsonArrays) {
        JSONArray res = new JSONArray();
        
        for (String jsonArray : jsonArrays) {
            jsonArray = jsonArray.substring(1, jsonArray.length() - 1);
            res.appendElement(jsonArray);
        }
        //System.out.println(res.toJSONString());
        return res.toJSONString().replaceAll("\\\\", "'").replaceAll("\"", " ").replaceAll("'", "\"");
    }

    /**
     * Takes a list of json arrays and merges them into one string.
     *
     * @param jsonArrays
     * @return single string containing a json array.
     */
    public static String jsonArrayMerger(List<String> jsonArrays) {
        return jsonArrayMerger(jsonArrays.toArray(new String[0]));
    }
}
