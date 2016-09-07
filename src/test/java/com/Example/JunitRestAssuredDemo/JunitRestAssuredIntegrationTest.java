package com.Example.JunitRestAssuredDemo;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vishal on 9/7/16.
 */
public class JunitRestAssuredIntegrationTest {
    @Test
    public void testJsonParserWithSingleSetUsingRestAssured() throws IOException {

        String responseString =
                given()
                        .param("none", "none").
                        when()
                        .get("https://api.github.com/users/nealstewart").asString();

        Map jsonMap = from(responseString).get("");
        assertEquals("The given json is empty", jsonMap.isEmpty(), false);
        assertTrue("The Login Key is not present in json", jsonMap.containsKey("login"));
        assertTrue("The Login Value is not present json", jsonMap.containsValue("nealstewart"));
        assertEquals("The Login Value is not equals to nealstewart in json", jsonMap.get("login").toString(), "nealstewart");
        assertTrue("The json content length is not matching", jsonMap.size() == 30);

    }

    @Test
    public void testJsonParserWithMultipleSetUsingRestAssured() throws IOException {
        String responseString =
                given()
                        .param("none", "none").
                        when()
                        .get("https://api.github.com/users/nealstewart/repos").asString();
        ArrayList jsonList = from(responseString).get("");
        assertTrue("The json content length is not matching", jsonList.size() == 30);
        assertTrue("The json content length for the first set is not matching", (((HashMap) jsonList.get(0)).size() == 68));
        assertEquals("The json login value of first set is not nealstewart", ((((HashMap) ((HashMap) jsonList.get(0)).get("owner")).get("login"))).toString(), "nealstewart");
    }
}
