package con.zeos.experimental.scribembean;
import java.util.Map;

public interface ScribeStatusMBean {
  public Map<String, Map<String, Long>> getStatuses();
}