package eldarvenes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PostEnergyToWeb implements Job {

    public PostEnergyToWeb(){
        loadProperties();
    }

    ReadFromYouless rfy = new ReadFromYouless();
    String p_name = "";
    String p_pass = "";

    public void saveReport() throws SQLException, InterruptedException {
        FirefoxDriver driver = new FirefoxDriver();

        driver.get("https://kundeweb.sognekraft.no/pls/kundeweb_sognekraft/webuser.avlesning.controlProc?p_session_id=217990");

        WebElement element = driver.findElement(By.name("p_navn"));
        element.sendKeys(p_name);

        element = driver.findElement(By.name("p_pass"));
        element.sendKeys(p_pass);
        element.submit();

        // sjekker om ein kjem til oppdatering av kundeprofilsidene.
        try {
            if (driver.findElement(By.id("id_profildialog")) != null) {
                WebElement submitKnapp = driver.findElement(By.className("elisBtn"));
                submitKnapp.submit();
            }

            if (driver.findElementByLinkText("målaravlesing") != null) {
                WebElement linkTilMaalaravlesing = driver.findElementByLinkText("målaravlesing");
                linkTilMaalaravlesing.click();
            }
        } catch (Exception e) {
        }

        element = driver.findElement(By.name("p_maalarStand"));
        Double kwh_double = rfy.getTotalKwhFromYouLess();
        Long tmp_kwh = Math.round(kwh_double);
        String kwh = String.valueOf(tmp_kwh);

        //sendMessageToEmail("Automatisk målarregistrering var vellykka! \n Det vart registrertmålarstand på: " + kwh + "kwh");
        System.out.println("Registrerer: " + kwh + " kwh til "
                + driver.getTitle());
        element.sendKeys(kwh);
        element.submit();
        Thread.sleep(1000);
        element = driver.findElement(By.className("elisBtn"));
        element.click();

        if (driver.findElement(By.cssSelector("body")).getText()
                .contains("kvittering")) {
            System.out.println("Registrering var velykka");
            //sendMessageToEmail("Automatisk målarregistrering var vellykka! \n Det vart registrertmålarstand på: " + kwh + "kwh");
        } else {
            System.out
                    .println("Noko gjekk gale med registrering av forbruk");
        }

        driver.close();
        driver.quit();

    }

    private void sendMessageToEmail(String melding) {
        EmailMessage epost = new EmailMessage();
        epost.setText(melding);
        epost.setFrom("eldarvenes@gmail.com");
        epost.setTo("eldarvenes@gmail.com");
        epost.setSubject("Automatisk registrering av strømavlesing til kundeweb");

    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            saveReport();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
