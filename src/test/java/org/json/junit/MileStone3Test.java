package org.json.junit;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import static org.junit.Assert.assertEquals;

public class MileStone3Test {
    /**
     * Test1
     */
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
        }
        catch (IOException e){
            System.out.println("Caught a IO Exception ");
            e.printStackTrace();
        }
    }

    /**
     * Test 2
     */
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
            assertEquals("Correct result.","{\"SWE262_catalog\":{\"SWE262_book\":[{\"SWE262_author\":\"Gambardella, Matthew\",\"SWE262_title\":\"XML Developer's Guide\",\"SWE262_publish_date\":\"2000-10-01\",\"SWE262_genre\":\"Computer\",\"SWE262_description\":\"An in-depth look at creating applications\\n            with XML.\",\"SWE262_id\":\"bk101\",\"SWE262_price\":44.95},{\"SWE262_author\":\"Ralls, Kim\",\"SWE262_title\":\"Midnight Rain\",\"SWE262_publish_date\":\"2000-12-16\",\"SWE262_genre\":\"Fantasy\",\"SWE262_description\":\"A former architect battles corporate zombies,\\n            an evil sorceress, and her own childhood to become queen\\n            of the world.\",\"SWE262_id\":\"bk102\",\"SWE262_price\":5.95},{\"SWE262_author\":\"Corets, Eva\",\"SWE262_title\":\"Maeve Ascendant\",\"SWE262_publish_date\":\"2000-11-17\",\"SWE262_genre\":\"Fantasy\",\"SWE262_description\":\"After the collapse of a nanotechnology\\n            society in England, the young survivors lay the\\n            foundation for a new society.\",\"SWE262_id\":\"bk103\",\"SWE262_price\":5.95}]}}",
                    jo.toString());
        }
        catch (IOException e){
            System.out.println("Caught a IO Exception ");
            e.printStackTrace();
        }
    }
}
