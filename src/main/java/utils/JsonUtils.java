package utils;

import java.util.List;

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
        StringBuilder sb = new StringBuilder("{");

        for (String jsonArray : jsonArrays) {
            jsonArray = jsonArray.substring(1, jsonArray.length() - 1);

            sb.append(jsonArray);
            sb.append(",");
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
    public static String jsonArrayMerger(List<String> jsonArrays)
    {
        return jsonArrayMerger(jsonArrays.toArray(new String[0]));
    }
}
