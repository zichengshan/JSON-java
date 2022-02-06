package org.json.junit;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class MileStone2Test {
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
    public void handleReadArraySubObject() {
        String xmlStr ="<?xml version=\"1.0\"?>\n" +
                "<catalog>\n" +
                "    <book id=\"bk101\">\n" +
                "        <author>Gambardella, Matthew</author>\n" +
                "        <title>XML Developer's Guide</title>\n" +
                "        <genre>Computer</genre>\n" +
                "        <price>44.95</price>\n" +
                "        <publish_date>2000-10-01</publish_date>\n" +
                "        <description>An in-depth look at creating applications\n" +
                "            with XML.</description>\n" +
                "    </book>\n" +
                "    <book id=\"bk102\">\n" +
                "        <author>Ralls, Kim</author>\n" +
                "        <title>Midnight Rain</title>\n" +
                "        <genre>Fantasy</genre>\n" +
                "        <price>5.95</price>\n" +
                "        <publish_date>2000-12-16</publish_date>\n" +
                "        <description>A former architect battles corporate zombies,\n" +
                "            an evil sorceress, and her own childhood to become queen\n" +
                "            of the world.</description>\n" +
                "    </book>\n" +
                "    <book id=\"bk103\">\n" +
                "        <author>Corets, Eva</author>\n" +
                "        <title>Maeve Ascendant</title>\n" +
                "        <genre>Fantasy</genre>\n" +
                "        <price>5.95</price>\n" +
                "        <publish_date>2000-11-17</publish_date>\n" +
                "        <description>After the collapse of a nanotechnology\n" +
                "            society in England, the young survivors lay the\n" +
                "            foundation for a new society.</description>\n" +
                "    </book>\n" +
                "</catalog>";

        try {

            JSONObject jobj = XML.toJSONObject(new StringReader(xmlStr), new JSONPointer("/catalog/book/1"));
            assertEquals("Correct result.","{\"book\":{\"author\":\"Ralls, Kim\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"A former architect battles corporate zombies,\\n            an evil sorceress, and her own childhood to become queen\\n            of the world.\",\"id\":\"bk102\",\"title\":\"Midnight Rain\",\"publish_date\":\"2000-12-16\"}}",jobj.toString());
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
    @Test
    public void handleTransformation(){
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
            JSONObject jo = XML.toJSONObject(reader, s -> "SWE262_" + s);
            reader.close();
            assertEquals("Correct result.","{\"SWE262_contact\":{\"SWE262_name\":\"Crista Lopes\",\"SWE262_nick\":\"Crista\",\"SWE262_address\":{\"SWE262_street\":\"Ave of Nowhere\",\"SWE262_zipcode\":92614}}}",
                    jo.toString());
            System.out.println(jo.toString(4));
        }
            catch (IOException e){
            System.out.println("Caught a IO Exception ");
            e.printStackTrace();
        }
    }

    @Test
    public void handleTransformation2(){
        String xmlStr ="<?xml version=\"1.0\"?>\n" +
                "<catalog>\n" +
                "    <book id=\"bk101\">\n" +
                "        <author>Gambardella, Matthew</author>\n" +
                "        <title>XML Developer's Guide</title>\n" +
                "        <genre>Computer</genre>\n" +
                "        <price>44.95</price>\n" +
                "        <publish_date>2000-10-01</publish_date>\n" +
                "        <description>An in-depth look at creating applications\n" +
                "            with XML.</description>\n" +
                "    </book>\n" +
                "    <book id=\"bk102\">\n" +
                "        <author>Ralls, Kim</author>\n" +
                "        <title>Midnight Rain</title>\n" +
                "        <genre>Fantasy</genre>\n" +
                "        <price>5.95</price>\n" +
                "        <publish_date>2000-12-16</publish_date>\n" +
                "        <description>A former architect battles corporate zombies,\n" +
                "            an evil sorceress, and her own childhood to become queen\n" +
                "            of the world.</description>\n" +
                "    </book>\n" +
                "    <book id=\"bk103\">\n" +
                "        <author>Corets, Eva</author>\n" +
                "        <title>Maeve Ascendant</title>\n" +
                "        <genre>Fantasy</genre>\n" +
                "        <price>5.95</price>\n" +
                "        <publish_date>2000-11-17</publish_date>\n" +
                "        <description>After the collapse of a nanotechnology\n" +
                "            society in England, the young survivors lay the\n" +
                "            foundation for a new society.</description>\n" +
                "    </book>\n" +
                "</catalog>";
        try {
            Reader reader = new StringReader(xmlStr);
            JSONObject jo = XML.toJSONObject(reader, s -> "SWE262_" + s);
            reader.close();
            assertEquals("Correct result.","{\"SWE262_contact\":{\"SWE262_name\":\"Crista Lopes\",\"SWE262_nick\":\"Crista\",\"SWE262_address\":{\"SWE262_street\":\"Ave of Nowhere\",\"SWE262_zipcode\":92614}}}",
                    jo.toString());
            System.out.println(jo.toString(4));
        }
        catch (IOException e){
            System.out.println("Caught a IO Exception ");
            e.printStackTrace();
        }
    }
}
