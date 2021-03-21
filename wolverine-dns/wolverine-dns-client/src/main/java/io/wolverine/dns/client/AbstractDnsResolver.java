package io.wolverine.dns.client;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelOption;
import io.wolverine.dns.client.config.DnsConfig;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpInbound;
import reactor.netty.udp.UdpOutbound;

public abstract class AbstractDnsResolver implements DnsResolver{
	private List<DnsServer> nameservers;
	private NettyOutbound createUdpOutbound(String ip) {
		Connection connection =
				UdpClient.create()
				         .host(ip)
				         .port(1053)
				         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				         .connectNow(Duration.ofSeconds(30));
		return connection.outbound();
	}
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
	protected abstract String getHostFromRemote(String json) ;
	protected abstract String getHostFromLocal(String hostname) ;
	protected abstract List<String> getHostListFromRemote(String json) ;
	protected abstract List<String> getHostListFromLocal(String servicename) ;
	@Override
	public String getIp(String hostname) {
		String ip = getHostFromLocal(hostname);
		if(ip != null) { return ip;}
		return this.getHostFromRemote(this.composeHostReq(hostname));
		/*Consumer<String> consumer = new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println("xxxx" + t);
			}
			
		};
		CoreSubscriber<ByteBuf> subscriber = new CoreSubscriber<ByteBuf>() {
			@Override
			public void onNext(ByteBuf t) {
				// TODO Auto-generated method stub
				System.out.println(t);
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSubscribe(Subscription s) {
				// TODO Auto-generated method stub
				System.out.println("onSubscribe");
				s.request(1);
			}
			
		};
		
		UdpClient udpClient =
				UdpClient.create()
				         .host(dnsIp)
				         .port(1053)
				         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				         .wiretap(true)
				         //.doOnChannelInit((observer, channel, remoteAddress) ->
				         //	channel.pipeline().addFirst(new LoggingHandler("reactor.netty.examples"))) 
				         .handle( new BiFunction<UdpInbound, UdpOutbound, Publisher<Void>>(){
							@Override
							public Publisher<Void> apply(UdpInbound in, UdpOutbound out) {
								System.out.println("Publisher<Void> apply(" + in + out);
								//out.sendString(Mono.just(json)).then().;
								in.receive().subscribe(subscriber);
								NettyOutbound outbound = out.sendString(Mono.just(json));
								outbound.then().subscribe();
								return outbound;
							}
				         });
                
		Connection conn = udpClient.connectNow();
		conn.onDispose().block();
		
		return null;//*/
	}
	@Override
	public List<String> getIps(String servicename) {
		List<String> ips = getHostListFromLocal(servicename);
		if(ips != null && ips.size() > 0) { return ips;}
		return this.getHostListFromRemote(this.composeHostListReq(servicename));
	}
	public static void main(String[] args) {
		AbstractDnsResolver resolver = new AbstractDnsResolver() {

			@Override
			protected String getHostFromRemote(String json) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected String getHostFromLocal(String hostname) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected List<String> getHostListFromRemote(String json) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected List<String> getHostListFromLocal(String servicename) {
				// TODO Auto-generated method stub
				return null;
			}};
		resolver.getIp("www.sina.com.cn");
		
	}
}
