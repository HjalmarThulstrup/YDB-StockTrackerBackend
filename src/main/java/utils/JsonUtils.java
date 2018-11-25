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
        
        for (int i = 0; i < jsonArrays.length; i++) {
            res.appendElement(jsonArrays[i]);
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
