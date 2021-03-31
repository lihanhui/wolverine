package io.wolverine.dns.client.resolver;

import io.nezha.event.AsyncResult;

public interface DnsClient {
	void getServiceHost(String hostname, AsyncResult asyncResult);
	void getMultiServiceHosts(String servicename, AsyncResult asyncResult); 
}
