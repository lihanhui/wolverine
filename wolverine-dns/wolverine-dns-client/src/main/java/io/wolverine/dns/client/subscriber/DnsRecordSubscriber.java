package io.wolverine.dns.client.subscriber;

import java.nio.charset.Charset;

import io.doraemon.json.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.nezha.event.AsyncResult;
import io.wolverine.dns.client.ServiceHost;

public class DnsRecordSubscriber extends AbstractDnsRecordSubscriber{
	public DnsRecordSubscriber(AsyncResult asyncResult) {
		super(asyncResult) ;
	}
	@Override
	protected Object parseResult(ByteBuf t) {
		String json =
				t.getCharSequence(0, t.readableBytes(), Charset.defaultCharset()).toString();
		return JsonUtil.fromJson(json, ServiceHost.class);
	}
}
