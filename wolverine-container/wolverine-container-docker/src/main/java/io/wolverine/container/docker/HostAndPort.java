package io.wolverine.container.docker;

public class HostAndPort {
	private String host;
	private int port;
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
	@Override
	public String toString() {
		return "HostAndPort [host=" + host + ", port=" + port + "]";
	}
	public HostAndPort(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
}
