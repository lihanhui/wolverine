package io.wolverine.dns.client.subscriber;

import java.nio.charset.Charset;

import io.doraemon.json.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.nezha.event.AsyncResult;
import io.wolverine.dns.client.MultiServiceHosts;

public class MultiDnsRecordsSubscriber extends AbstractDnsRecordSubscriber{
	public MultiDnsRecordsSubscriber(AsyncResult asyncResult) {
		super(asyncResult) ;
	}
	@Override
	protected Object parseResult(ByteBuf t) {
		String json =
				t.getCharSequence(0, t.readableBytes(), Charset.defaultCharset()).toString();
		return JsonUtil.fromJson(json, MultiServiceHosts.class);
	}
}
