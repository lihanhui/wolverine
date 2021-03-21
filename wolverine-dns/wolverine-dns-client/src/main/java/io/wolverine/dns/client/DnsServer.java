package io.wolverine.dns.client;

public class DnsServer {
	private String ip;
	private int port;
	private Boolean active;
	
	public DnsServer(String ip, int port, Boolean active) {
		super();
		this.ip = ip;
		this.port = port;
		this.active = active;
	}
	public DnsServer(String ip, int port) {
		this(ip, port, true);
	}
	public DnsServer(String ip) {
		this(ip, 1053, true);
	}
	public DnsServer() {
		this("localhost", 1053, true);
	}
	public String getIp() {
		return ip;
	}
	public Boolean active() {
		return active;
	}
	public void active(Boolean active) {
		this.active = active;
	}
	public int getPort() {
		return port;
	}
	@Override
	public String toString() {
		return "DnsServer [ip=" + ip + ", port=" + port + ", active=" + active + "]";
	}
}
