package eldarvenes;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Store implements Job {

	ReadFromYouless rfy = new ReadFromYouless();

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		StoreDataFile storeToFile = new StoreDataFile();
		storeToFile.write(String.valueOf(rfy.getTotalKwhFromYouLess()));



	}
}