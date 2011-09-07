package con.zeos.experimental.scribembean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ScribeStatusMBeanRunner {
  public static void main(String[] args) throws Exception {
	ScribeStatus cache = new ScribeStatus();
	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	ObjectName name = new ObjectName("com.zeos.experimental:type=ScribeStatusMBean");
	mbs.registerMBean(cache, name);
	imitateActivity(cache);
  }

  private static void imitateActivity(final ScribeStatus status) {
	ScheduledThreadPoolExecutor stp = new ScheduledThreadPoolExecutor(1);
	stp.scheduleAtFixedRate(new Runnable() {

	  @Override
	  public void run() {
		status.resetStatuses();
	  }
	}, 1, 1, TimeUnit.SECONDS);
  }
}
