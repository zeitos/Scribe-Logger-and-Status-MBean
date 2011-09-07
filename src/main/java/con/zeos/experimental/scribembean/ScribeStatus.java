package con.zeos.experimental.scribembean;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

import con.zeos.experimental.scribe.log.scribe;


public class ScribeStatus implements ScribeStatusMBean {

  public class ScribeNode {
	private final String host;
	private final int port;

	public ScribeNode(String host, int port) {
	  this.host = host;
	  this.port = port;
	}

	public String getHost() {
	  return host;
	}

	public int getPort() {
	  return port;
	}
  }

  private Map<String, Map<String, Long>> statuses;

  private static Map<String, Long> disconnectedError;
  private static List<ScribeNode> scribeNodes;
  {
	scribeNodes = new ArrayList<ScribeNode>();
	scribeNodes.add(new ScribeNode("localhost", 1464)); // Server
	scribeNodes.add(new ScribeNode("localhost", 1463)); // local

	disconnectedError = new HashMap<String, Long>();
	disconnectedError.put("Disconnected", 1l);
  }

  public ScribeStatus() {
	resetStatuses();
  }

  public Map<String, Map<String, Long>> getStatuses() {
	return statuses;
  }

  public void resetStatuses() {

	statuses = new HashMap<String, Map<String, Long>>();
	for (ScribeNode node : scribeNodes) {
	  TSocket sock;
	  try {
		sock = new TSocket(new Socket(node.getHost(), node.getPort()));
		TFramedTransport transport = new TFramedTransport(sock);
		TBinaryProtocol protocol = new TBinaryProtocol(transport, false, false);
		scribe.Client client = new scribe.Client(protocol, protocol);

		statuses.put(node.getHost() + ":" + node.getPort(), client.getCounters());

	  } catch (Exception e) {
		statuses.put(node.getHost() + ":" + node.getPort(), disconnectedError);
		e.printStackTrace();
	  }

	}

  }
}
