package io.wolverine.dns.client.resolver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.reactivestreams.Publisher;

import io.doraemon.restful.ResultMsg;
import io.netty.channel.ChannelOption;
import io.nezha.event.AsyncResult;
import io.nezha.event.Result;
import io.wolverine.dns.client.DnsServer;
import io.wolverine.dns.client.config.DnsConfig;
import io.wolverine.dns.client.listener.RecordListener;
import io.wolverine.dns.client.record.MultiServiceHosts;
import io.wolverine.dns.client.record.ServiceHost;
import io.wolverine.dns.client.subscriber.DnsRecordSubscriber;
import io.wolverine.dns.client.subscriber.MultiDnsRecordsSubscriber;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpInbound;
import reactor.netty.udp.UdpOutbound;

public abstract class AbstractDnsResolver implements DnsResolver, RecordListener{
	private List<DnsServer> nameservers;
	// TODO: let request wait for the dns response;
	public AbstractDnsResolver() {
		nameservers = new ArrayList<>();
		DnsConfig.init();
		for(String ip: DnsConfig.getDnsConfig().getNameservers()) {
			nameservers.add(new DnsServer(ip));
		}
	}
	protected String getDnsServerIp() {
		for(DnsServer dns: this.nameservers) {
			if(dns.active()) {
				return dns.getIp();
			}
		}
		return null;
	}
	private String composeHostReq(String hostname) {
		final String json = "{" +  
		    "\"method\":\"translate-domain-name\"," + 
		    "\"hostname\": \"%s\"}";
		return String.format(json, hostname);
	}
	private String composeHostListReq(String servicename) {
		final String json = "{" +  
		    "\"method\":\"translate-domain-name\"," + 
		    "\"servicename\": \"%s\"}";
		return String.format(json, servicename);
	}
	private void getServiceHostFromRemote(String json, AsyncResult asyncResult) {
		UdpClient udpClient =
				UdpClient.create()
				         .host(getDnsServerIp())
				         .port(1053)
				         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				         .wiretap(true)
				         .handle( new BiFunction<UdpInbound, UdpOutbound, Publisher<Void>>(){
							@Override
							public Publisher<Void> apply(UdpInbound in, UdpOutbound out) {
								System.out.println("Publisher<Void> apply(" + in + out);
								in.receive().subscribe(new DnsRecordSubscriber(AbstractDnsResolver.this, asyncResult));
								NettyOutbound outbound = out.sendString(Mono.just(json));
								outbound.then().subscribe();
								return outbound.neverComplete();
							}
				         });
                
		udpClient.connect();
	}
	
	private void getMultiServiceHostsFromRemote(String json, AsyncResult asyncResult) {
		UdpClient udpClient =
				UdpClient.create()
				         .host(getDnsServerIp())
				         .port(1053)
				         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				         .wiretap(true)
				         .handle( new BiFunction<UdpInbound, UdpOutbound, Publisher<Void>>(){
							@Override
							public Publisher<Void> apply(UdpInbound in, UdpOutbound out) {
								System.out.println("Publisher<Void> apply(" + in + out);
								in.receive().subscribe(new MultiDnsRecordsSubscriber(AbstractDnsResolver.this, asyncResult));
								NettyOutbound outbound = out.sendString(Mono.just(json));
								outbound.then().subscribe();
								return outbound.neverComplete();
							}
				         });
                
		udpClient.connect();
	}
	protected abstract ServiceHost getServiceHostFromLocal(String hostname) ;
	protected abstract MultiServiceHosts getMultiServiceHostsFromLocal(String servicename) ;
	
	@Override
	public void getServiceHost(String hostname, AsyncResult asyncResult) {
		ServiceHost serviceHost = getServiceHostFromLocal(hostname);
		if(serviceHost != null) { 
			asyncResult.setResult(new Result<>(new ResultMsg<>(serviceHost)));
			return;
		}
		getServiceHostFromRemote(composeHostReq(hostname), asyncResult);
	}
	@Override
	public void getMultiServiceHosts(String servicename, AsyncResult asyncResult) {
		MultiServiceHosts multiServiceHosts = this.getMultiServiceHostsFromLocal(servicename);
		if(multiServiceHosts != null) { 
			asyncResult.setResult(new Result<>(new ResultMsg<>(multiServiceHosts)));
			return;
		}
		
		this.getMultiServiceHostsFromRemote(this.composeHostListReq(servicename), asyncResult);
	}
	public static void main(String[] args) {
		AbstractDnsResolver resolver = new AbstractDnsResolver() {

			@Override
			protected ServiceHost getServiceHostFromLocal(String hostname) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected MultiServiceHosts getMultiServiceHostsFromLocal(String servicename) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void onServiceHost(ServiceHost serviceHost) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMultiServiceHosts(MultiServiceHosts multiServiceHosts) {
				// TODO Auto-generated method stub
				
			}
		
		};
		resolver.getServiceHost("www.sina.com.cn", null);
	}
}
