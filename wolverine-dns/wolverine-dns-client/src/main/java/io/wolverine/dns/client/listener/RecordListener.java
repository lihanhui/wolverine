package io.wolverine.dns.client.listener;

import io.wolverine.dns.client.record.MultiServiceHosts;
import io.wolverine.dns.client.record.ServiceHost;

public interface RecordListener {
	void onServiceHost(ServiceHost serviceHost);
	void onMultiServiceHosts(MultiServiceHosts multiServiceHosts);
}
