package utils;

import java.io.Serializable;

@SuppressWarnings("all")
public class Connectinfor implements Serializable{
	
	private String host;
	private int port;
	private String sourceFilePath;
	private String destinationFilePath;
	
	public Connectinfor() {
		super();
	}
	
	public Connectinfor(String host, int port, String sourceFilePath, String destinationFilePath) {
		super();
		this.host = host;
		this.port = port;
		this.sourceFilePath = sourceFilePath;
		this.destinationFilePath = destinationFilePath;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public String getDestinationFilePath() {
		return destinationFilePath;
	}

	public void setDestinationFilePath(String destinationFilePath) {
		this.destinationFilePath = destinationFilePath;
	}
}
