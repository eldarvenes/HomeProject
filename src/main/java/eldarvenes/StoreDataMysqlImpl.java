package eldarvenes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by remote on 13.03.2016.
 */
public class StoreDataMysqlImpl implements StoreData{
    public void write(String kwhTotal) {
        //TODO implement store to mysql
        /**try {
            URL youless = new URL("http://10.0.0.14/a?f=j");

            BufferedReader in;

            in = new BufferedReader(new InputStreamReader(youless.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                JSONObject json = new JSONObject(inputLine);

                String kwh = json.getString("cnt");

                kwh = kwh.replace(',', '.');
                double valuekwh = Double.valueOf(kwh);

                String INSERT_RECORD = "insert into power(timestamp, kwh) values(?, ?)";
                Connector.getInstance();
                Connection connection = Connector.getConnection();
                PreparedStatement pstmt = connection
                        .prepareStatement(INSERT_RECORD);
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(
                        new java.util.Date().getTime());
                pstmt.setTimestamp(1, sqlDate);
                pstmt.setDouble(2, valuekwh);
                pstmt.executeUpdate();
                pstmt.close();
                connection.close();

                System.out.println("Store value: " + valuekwh + " to database");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
