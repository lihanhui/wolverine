package io.wolverine.dns.client.subscriber;

import java.nio.charset.Charset;

import io.doraemon.json.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.nezha.event.AsyncResult;
import io.wolverine.dns.client.listener.RecordListener;
import io.wolverine.dns.client.record.ServiceHost;

public class DnsRecordSubscriber extends AbstractDnsRecordSubscriber{
	public DnsRecordSubscriber(RecordListener recordListener, AsyncResult asyncResult) {
		super(recordListener, asyncResult);
	}
	@Override
	protected Object parseResult(ByteBuf t) {
		String json =
				t.getCharSequence(0, t.readableBytes(), Charset.defaultCharset()).toString();
		return JsonUtil.fromJson(json, ServiceHost.class);
	}
	@Override
	protected void notify(Object record) {
		if(record == null || record instanceof ServiceHost) {
			getRecordListener().onServiceHost((ServiceHost)record);
		}
	}
}
