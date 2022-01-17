package org.json.junit.data;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class mileStone2Test {
    @Test
    public void handleReadSubObject() {
        String xmlStr ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";

        try {

            JSONObject jobj = XML.toJSONObject(new StringReader(xmlStr), new JSONPointer("/contact/address/street/"));
            assertEquals("Correct result.","{\"street\":\"Ave of Nowhere\"}",jobj.toString());
        } catch (JSONException | IOException e) {
            System.out.println("Caught a JSON Exception ");
            e.printStackTrace();
        }
    }

    @Test
    public void handleReadWrongSub() {
        String xmlStr ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";

        try {
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlStr), new JSONPointer("/contact/address/nick/"));
        } catch (JSONException | IOException e) {
            assertEquals("Expecting an exception message",
                    "Misshaped element at 200 [character 10 line 9]",
                    e.getMessage());
        }
    }

    @Test
    public void handleReplacement() {
        String xmlStr ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";

        try {
            Reader reader = new StringReader(xmlStr);
            JSONPointer pointer = new JSONPointer("/contact/address/street/");
            JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n");
            JSONObject jo = XML.toJSONObject(reader, pointer,replacement);
            String newValue =jo.toString();
            reader.close();
            assertEquals("Correct result","{\"contact\":{\"nick\":\"Crista\",\"address\":{\"zipcode\":92614,\"street\":{\"street\":\"Ave of the Arts\"}},\"name\":\"Crista Lopes\"}}", newValue);
        } catch (JSONException e) {
            System.out.println("Caught a JSON Exception ");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Caught a IO Exception ");
            e.printStackTrace();
        }
    }
    @Test
    public void handleWrongReplacement() {
        String xmlStr ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";

        try {
            Reader reader = new StringReader(xmlStr);
            JSONPointer pointer = new JSONPointer("/contact/address/nick/");
            JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n");
            JSONObject jo = XML.toJSONObject(reader, pointer,replacement);
            reader.close();
        } catch (JSONException e) {
            assertEquals("Expecting an exception message",
                    "JSONObject[\"nick\"] not found.",
                    e.getMessage());
        } catch (IOException e){
            System.out.println("Caught a IO Exception ");
            e.printStackTrace();
        }
    }
}
