package eldarvenes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    public String getTotalKwhAsString(){
        return getStringValueNullDecimals(getTotalKwhFromYouLess());
    }

    private String getStringValueNullDecimals(double kwh){

        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        String str = nf.format(kwh);

        return str.replace('\u00A0',' ').replaceAll("\\s","");
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
