package io.wolverine.dns.client.subscriber;

import org.reactivestreams.Subscription;

import io.doraemon.restful.ResultMsg;
import io.netty.buffer.ByteBuf;
import io.nezha.event.AsyncResult;
import io.nezha.event.Result;
import reactor.core.CoreSubscriber;

public abstract class AbstractDnsRecordSubscriber implements CoreSubscriber<ByteBuf>{
	private AsyncResult asyncResult;
	public AbstractDnsRecordSubscriber(AsyncResult asyncResult) {
		this.asyncResult = asyncResult;
	}
	
	protected abstract Object parseResult(ByteBuf t);
	@Override
	public void onNext(ByteBuf t) {
		asyncResult.setResult(new Result<>(new ResultMsg<>(this.parseResult(t))));
	}

	@Override
	public void onError(Throwable t) {
		asyncResult.setResult(new Result<>(t, "some exception"));
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSubscribe(Subscription s) {
		// TODO Auto-generated method stub
		
	}

}
