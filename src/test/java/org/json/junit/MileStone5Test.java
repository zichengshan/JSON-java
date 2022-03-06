package org.json.junit;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MileStone5Test {
    private String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<contact>\n" +
            "  <nick>Crista </nick>\n" +
            "  <name>Crista Lopes</name>\n" +
            "  <address>\n" +
            "    <street>Ave of Nowhere</street>\n" +
            "    <zipcode>92614</zipcode>\n" +
            "  </address>\n" +
            "</contact>";
    private String result = "{\"contact\":{\"nick\":\"Crista\",\"address\":{\"zipcode\":92614,\"street\":\"Ave of Nowhere\"},\"name\":\"Crista Lopes\"}}";

    /**
     * Test whether the function can handle with XML successfully.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testSuccess() throws ExecutionException, InterruptedException {

        Reader reader = new StringReader(xml);

        Future<JSONObject> future = XML.toJSONObjectMS5(reader);
        JSONObject obj = null;
        try{
            obj = future.get();
            assertEquals(obj.toString(),result);
        }catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
            e.printStackTrace();
        }
    }

    /**
     * Test whether the function can handle with exception.
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test(expected=ExecutionException.class)
    public void shouldThrowExecutionException() throws InterruptedException, ExecutionException {
        String str = "<contact";
        Reader reader = new StringReader(str);
        Future<JSONObject> futureJSONObject = XML.toJSONObjectMS5(reader);
        while (!futureJSONObject.isDone()) {
            Thread.sleep(1000);
        }
        JSONObject jo = futureJSONObject.get();
        assertTrue("The Json would be empty because of exception", jo.isEmpty());
    }
}
