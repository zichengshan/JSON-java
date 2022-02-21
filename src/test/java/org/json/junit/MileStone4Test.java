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
        Stream<Node> stream = jsonObject.toStream();
        List<Node> l = stream.collect(Collectors.toList());
        int i =0;
        for (Node node : l){
            System.out.println(i);
            if(node.getData() instanceof JSONObject) {
                System.out.print(node.getKey()+" : ");
                System.out.println(((JSONObject) node.getData()).toString());
            }
            else if(node.getData() instanceof JSONArray) {
                for(int j= 0;j< ((JSONArray) node.getData()).length(); j++){
                    System.out.print(node.getKey()+" : ");
                    System.out.println(((Object)((JSONArray) node.getData()).get(j)).toString());
                }

            }
            else {
                System.out.print(node.getKey()+" : ");
                System.out.println(node.getData().toString());
            }

            i++;
        }
//        int i=0;
//        List<JSONObject> list = stream.collect(Collectors.toList());
//        assertEquals("{\"author\":\"Kress Peter\"}",list.get(0).toString());
    }
}
