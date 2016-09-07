package com.Example.JunitRestAssuredDemo;

import org.junit.Test;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonParserTest {
    @Test
    public void testJsonParserWithSingleSet() throws Exception {
        JsonParser jsonParserObject = new JsonParser();
        String result = jsonParserObject.readJsonFromUrl("https://api.github.com/users/nealstewart");
        Map jsonMap = jsonParserObject.stringJsonToJsonArray(result);
        assertEquals("The given json is empty", jsonMap.isEmpty(), false);
        assertTrue("The Login Key is not present in json", jsonMap.containsKey("login"));
        assertTrue("The Login Value is not present json", jsonMap.containsValue("nealstewart"));
        assertEquals("The Login Value is not equals to nealstewart in json", jsonMap.get("login").toString(), "nealstewart");
        assertTrue("The json content length is not matching", jsonMap.size() == 30);
    }

    @Test
    public void testJsonParserWithMultipleSet() throws Exception {
        JsonParser jsonParserObject = new JsonParser();
        String result = jsonParserObject.readJsonFromUrl("https://api.github.com/users/nealstewart/repos");
        List jsonList = jsonParserObject.stringJsonArrayToJsonArray(result);
        assertTrue("The json content length is not matching", jsonList.size() == 30);
        assertTrue("The json content length for the first set is not matching", ((Map) jsonList.toArray()[0]).size() == 68);
        assertEquals("The json login value of first set is not nealstewart", ((Map) ((Map) jsonList.toArray()[0]).get("owner")).get("login").toString(), "nealstewart");
    }


    @Test(expected = ConnectException.class)
    public void testJsonParserForException() throws Exception {
        JsonParser jsonParserObject = new JsonParser();
        jsonParserObject.readJsonFromUrl("https://api.github.xyz/users/something_not_have_anything");
    }
}