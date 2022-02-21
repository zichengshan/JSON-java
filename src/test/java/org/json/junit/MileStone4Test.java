package org.json.junit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.Node;
import org.json.XML;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MileStone4Test {
    @Test
    public void streamTest(){
        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";
        JSONObject jsonObject =
                XML.toJSONObject(xml);
        Stream<JSONObject> stream = jsonObject.toStream();
        List<JSONObject> l = stream.collect(Collectors.toList());
        int i =0;
        for (JSONObject node : l){
            System.out.println(i);
            System.out.println(node.toString());
            i++;
        }
//        int i=0;
//        List<JSONObject> list = stream.collect(Collectors.toList());
//        assertEquals("{\"author\":\"Kress Peter\"}",list.get(0).toString());
    }
}
