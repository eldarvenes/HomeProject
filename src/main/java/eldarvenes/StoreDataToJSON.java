package eldarvenes;

import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by remote on 02.04.2016.
 */
public class StoreDataToJSON {

    String filename;
    String filepath;

    public StoreDataToJSON(){
        loadProperties();
    }

    public void writeData(JSONObject kwhObject){


        FileWriter file = null;
        try {
            file = new FileWriter(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file != null) {
            try {
                file.write(kwhObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: "+kwhObject);

    }
    private void loadProperties() {
        Properties prop = new Properties();
        try {
            prop.load(Connector.class.getClassLoader()
                    .getResourceAsStream("configuration.properties"));
            filename = prop.getProperty("jsonFilename");
            filepath = prop.getProperty("local_filepath");
        } catch (Exception e) {

        }
    }




}
