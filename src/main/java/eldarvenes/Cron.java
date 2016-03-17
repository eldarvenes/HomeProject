package eldarvenes;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.*;

public class Cron {

    public static void main(String[] args) {

        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail lagre_til_fil = newJob(Store.class).withIdentity("lagre_til_fil", "group1")
                    .build();


            JobDetail storeKwh = newJob(Store.class).withIdentity("storeKwh", "group1")
                    .build();

            JobDetail sendReport = newJob(SendReport.class).withIdentity("sendReport", "group1")
                    .build();

            JobDetail saveReportToWeb = newJob(PostEnergyToWeb.class).withIdentity("saveReportToWeb", "group1")
                    .build();

            //Kvar time lagre til fil
            Trigger trigger_lagre_til_fil = newTrigger()
                    .withIdentity("trigger_lagre_til_fil")
                    .startNow()
                    .withSchedule(cronSchedule("0 * * * * ?"))
                    .build();

            //Kvar time, lagre til database
            Trigger trigger_storeKwh = newTrigger()
                    .withIdentity("trigger_storeKwh", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0 * * * * ?"))
                    .build();

            //Den første i kvar måned
            Trigger trigger_sendReport = newTrigger()
                    .withIdentity("trigger_sendReport", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0 0 * * * ?"))
                    .build();

            //Den første i kvar måned
            Trigger trigger_saveReportToWeb = newTrigger()
                    .withIdentity("trigger_saveReportToWeb", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0 0 0 1 * ?"))
                    .build();

            System.out.println("Scheduler Started...");
            scheduler.start();
            scheduler.scheduleJob(lagre_til_fil, trigger_lagre_til_fil);
            //scheduler.scheduleJob(storeKwh, trigger_storeKwh);
            //scheduler.scheduleJob(sendReport, trigger_sendReport);
            //scheduler.scheduleJob(saveReportToWeb, trigger_saveReportToWeb);

            //scheduler.shutdown();
            //System.out.println("Scheduler Stopped..");

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


}
