package eldarvenes;

import junit.framework.TestCase;

/**
 * Created by remote on 13.03.2016.
 */
public class ReadFromYoulessTest extends TestCase {

    public void testGetTotalKwhFromYouLess() throws Exception {
        ReadFromYouless rfyl = new ReadFromYouless();
        System.out.println(rfyl.getTotalKwhFromYouLess());
    }

    public void testGetTotalKwhFromYouLessAsString() {
        ReadFromYouless rfyl = new ReadFromYouless();
        System.out.println(String.valueOf(rfyl.getTotalKwhAsString()));
    }
}