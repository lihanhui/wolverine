package io.wolverine.dns.client;

import java.util.List;

public interface DnsClient {
	String getIp(String hostname);
	List<String> getIps(String servicename);
}
