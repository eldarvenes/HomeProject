package eldarvenes;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;

/**
 * Created by remote on 02.04.2016.
 */
public class StoreDataToJSONTest extends TestCase {

    @Test
    public void testWriteJson(){
        KwhJson kwhJson = new KwhJson();
        kwhJson.writeKwhToJson(new Date().toString(), "123456");

        StoreDataToJSON storeDataToJSON = new StoreDataToJSON();
        storeDataToJSON.writeData(kwhJson.getJsonObject());
    }

}