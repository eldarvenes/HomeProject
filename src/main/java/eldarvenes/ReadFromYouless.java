package eldarvenes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by remote on 13.03.2016.
 */
public class ReadFromYouless {

    String youLessURL;

    public ReadFromYouless(){
        loadProperties();
    }

    public double getTotalKwhFromYouLess() {
        URL youless = null;
        try {
            youless = new URL(youLessURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;


        try {
            in = new BufferedReader(new InputStreamReader(youless.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputLine = null;
        try {
            inputLine = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject(inputLine);

        String kwh = json.getString("cnt");

        kwh = kwh.replace(',', '.');
        double valuekwh = Double.valueOf(kwh);

        return valuekwh;

    }

    private void loadProperties() {
        Properties prop = new Properties();
        try {
            prop.load(Connector.class.getClassLoader()
                    .getResourceAsStream("configuration.properties"));
            youLessURL = prop.getProperty("youLessURL");
        } catch (Exception e) {

        }
    }
}
