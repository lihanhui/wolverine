package io.wolverine.dns.client.subscriber;

import java.nio.charset.Charset;

import io.doraemon.json.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.nezha.event.AsyncResult;
import io.wolverine.dns.client.listener.RecordListener;
import io.wolverine.dns.client.record.MultiServiceHosts;
import io.wolverine.dns.client.record.ServiceHost;

public class MultiDnsRecordsSubscriber extends AbstractDnsRecordSubscriber{
	public MultiDnsRecordsSubscriber(RecordListener recordListener, AsyncResult asyncResult) {
		super(recordListener, asyncResult);
	}
	@Override
	protected Object parseResult(ByteBuf t) {
		String json =
				t.getCharSequence(0, t.readableBytes(), Charset.defaultCharset()).toString();
		return JsonUtil.fromJson(json, MultiServiceHosts.class);
	}
	@Override
	protected void notify(Object record) {
		if(record == null || record instanceof MultiServiceHosts) {
			getRecordListener().onMultiServiceHosts((MultiServiceHosts)record);
		}
	}
}
