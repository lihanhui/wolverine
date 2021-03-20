package io.wolverine.dns.client.config;

import java.io.File;
import java.util.List;

import io.doraemon.json.JsonUtil;

public class DnsConfig {
	private static String config = "/etc/wolverine-resolv.conf";
	private List<String> nameservers;
	private static DnsConfig dnsConfig = null;
	public static void init() {
		dnsConfig = JsonUtil.fromJsonFile(config, DnsConfig.class);
		return;
	}
	public static DnsConfig getDnsConfig() {
		return dnsConfig;
	}
	public List<String> getNameservers() {
		return nameservers;
	}
}
