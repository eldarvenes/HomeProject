package eldarvenes;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * Created by remote on 13.03.2016.
 */
public class StoreDataFile {

    String filename;
    String filepath;

    public StoreDataFile(){
        loadProperties();
    }

    public void write(String kwhTotal) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:HH:mm");

        Date timeNow = Calendar.getInstance().getTime();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath+filename, true)));
        } catch (IOException e) {
            e.printStackTrace();
        } {

        System.out.print("Write to file: ");
        writer.println(sdf.format(timeNow) + ":" + kwhTotal );
            System.out.println(sdf.format(timeNow) + ":" +  kwhTotal);

        writer.close();
    }
    }

    private void loadProperties() {
        Properties prop = new Properties();
        try {
            prop.load(Connector.class.getClassLoader()
                    .getResourceAsStream("configuration.properties"));
            filename = prop.getProperty("filename");
            filepath = prop.getProperty("filepath");
        } catch (Exception e) {

        }
    }
}
