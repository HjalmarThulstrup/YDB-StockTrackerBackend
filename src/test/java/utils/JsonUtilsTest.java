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
        String[] jsonArrays = {"{{Name=\"test1\"},{Name=\"test2\"}}", "{{Name=\"test4\"}}"};
        String expResult = "{{Name=\"test1\"},{Name=\"test2\"},{Name=\"test4\"}}";
        String result = JsonUtils.jsonArrayMerger(jsonArrays);
        System.out.println(result);
        System.out.println(expResult);

        assertEquals(expResult, result);
    }
}
