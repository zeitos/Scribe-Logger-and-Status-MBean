package con.zeos.experimental.scribe.log;
import org.apache.log4j.Logger;


public class TestLogger {
	private static Logger log = Logger.getLogger(TestLogger.class);
	public static void main(String[] args) {
		log.error("test : " +  System.currentTimeMillis());
		log.error("test2 :" +  System.currentTimeMillis());
	}

}
