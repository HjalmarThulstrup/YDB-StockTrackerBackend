package utils;

import java.util.List;

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
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < jsonArrays.length; i++) {
            jsonArrays[i] = jsonArrays[i].substring(1, jsonArrays[i].length() - 1);

            sb.append(jsonArrays[i]);
            if (i < jsonArrays.length-1) {
                sb.append(",");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString().trim();
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
