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
	private List<String> nameservers;
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
		nameservers.addAll(DnsConfig.getDnsConfig().getNameservers());
	}
	private String composeHostnameReq(String hostname) {
		final String json = "{" +  
		    "\"method\":\"translate-domain-name\"," + 
		    "\"hostname\": \"%s\"}";
		return String.format(json, hostname);
	}
	@Override
	public String getIp(String hostname) {
		String json = this.composeHostnameReq(hostname);
		String dnsIp = nameservers.get(0);
		Consumer<String> consumer = new Consumer<String>() {

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
				         .handle( new BiFunction<UdpInbound, UdpOutbound, Publisher<Void>>(){
							@Override
							public Publisher<Void> apply(UdpInbound in, UdpOutbound out) {
								System.out.println("Publisher<Void> apply(" + in + out);
								//out.sendString(Mono.just(json)).then().;
								in.receive().subscribe(subscriber);
								NettyOutbound outbound = out.sendString(Mono.just(json));
								return outbound.then();
							}
				         });
                
		// * */
		Connection conn = udpClient.connectNow();
		conn.onDispose().block();
		//udpClient.
		while(true) {
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		//Mono mono = connection.inbound().receive().then();
		//System.out.println(flux.asString().blockLast());
		//System.out.println(result);
		//connection.inbound().receive().;
		
		return null;
	}
	@Override
	public List<String> getIps(String servicename) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		AbstractDnsResolver resolver = new AbstractDnsResolver() {};
		resolver.getIp("www.sina.com.cn");
		
	}
}
