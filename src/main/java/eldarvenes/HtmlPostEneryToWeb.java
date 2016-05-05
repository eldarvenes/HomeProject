package eldarvenes;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by remote on 27.03.2016.
 */
public class HtmlPostEneryToWeb implements Job{

    String p_name;
    String p_pass;

    ReadFromYouless rfyl = new ReadFromYouless();
    //String maalerstand = rfyl.getTotalKwhAsString();

    String loginResult = "";
    private final String USER_AGENT = "Mozilla/5.0";



    HttpClient client = HttpClientBuilder.create()
            .setRedirectStrategy(new LaxRedirectStrategy())
            .build();


    public HtmlPostEneryToWeb(){
        loadProperties();
    }

    private String getLoginPage() throws IOException {
        URL url = new URL("https://kundeweb.sognekraft.no/pls/kundeweb_sognekraft/webuser.login.login?p_eltilbyder_id=3&p_kundetype=1");
        URLConnection con = url.openConnection();
        Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
        Matcher m = p.matcher(con.getContentType());

        String charset = m.matches() ? m.group(1) : "ISO-8859-1";
        Reader r = new InputStreamReader(con.getInputStream(), charset);
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        String str = buf.toString();

        return str;
    }

    public void login() throws Exception {
        String urlLogin = "https://kundeweb.sognekraft.no/pls/kundeweb_sognekraft/webuser.login.login_submit";
        HttpPost request = new HttpPost(urlLogin);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        request.setConfig(requestConfig);
        request.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("p_navn", p_name));
        urlParameters.add(new BasicNameValuePair("p_pass", p_pass));
        urlParameters.add(new BasicNameValuePair("p_applikasjons_id", "3"));
        urlParameters.add(new BasicNameValuePair("p_kundetype", "1"));
        urlParameters.add(new BasicNameValuePair("p_tilprosedyre", ""));

        request.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));


        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        loginResult = result.toString();
    }

    public void sendAvlesing() throws Exception {
        String dato = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String urlAvlesning = "https://kundeweb.sognekraft.no/pls/kundeweb_sognekraft/webuser.avlesning.submit";

        HttpPost post = new HttpPost(urlAvlesning);

        String maalerstand = rfyl.getTotalKwhAsString();
        maalerstand = maalerstand.replace(",", "");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("p_session_id", getSessionId(loginResult)));
        urlParameters.add(new BasicNameValuePair("p_avlesDato", dato));
        urlParameters.add(new BasicNameValuePair("p_maalarStand", maalerstand));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));


        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
    }

    public void godkjennAvlesing() throws Exception {

        String urlAvlesning = "https://kundeweb.sognekraft.no/pls/kundeweb_sognekraft/webuser.godkjenn_avlesning.submit";

        HttpPost post = new HttpPost(urlAvlesning);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("p_session_id", getSessionId(loginResult)));
        urlParameters.add(new BasicNameValuePair("p_funksjon", "1"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));


        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

    }

    public String getSessionId(String textToSearchIn) {
        String searchString = "p_session_Id=";

        int start = textToSearchIn.indexOf(searchString);
        String str = textToSearchIn;

        return (str.substring(start + 18, start + 24));
    }

    public void register(){
        try {
            System.out.println("Starter registrering til web...");
            getLoginPage();
            login();
            sendAvlesing();
            godkjennAvlesing();
            System.out.println("Registrering til web ferdig");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        register();
    }

    private void loadProperties() {
        Properties prop = new Properties();
        try {
            prop.load(Connector.class.getClassLoader()
                    .getResourceAsStream("configuration.properties"));
            p_name = prop.getProperty("p_name");
            p_pass = prop.getProperty("p_pass");
        } catch (Exception e) {

        }
    }

}




