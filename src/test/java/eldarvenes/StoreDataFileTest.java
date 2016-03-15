package eldarvenes;

import junit.framework.TestCase;

/**
 * Created by remote on 13.03.2016.
 */
public class StoreDataFileTest extends TestCase {

    public void testWrite() throws Exception {
        StoreDataFile storeDataFile = new StoreDataFile();
        storeDataFile.write("23234");
    }

    public void testFormat() {



    }
}
