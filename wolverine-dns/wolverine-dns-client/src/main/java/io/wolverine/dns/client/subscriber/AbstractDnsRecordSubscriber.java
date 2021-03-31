package io.wolverine.dns.client.subscriber;

import org.reactivestreams.Subscription;

import io.doraemon.restful.ResultMsg;
import io.netty.buffer.ByteBuf;
import io.nezha.event.AsyncResult;
import io.nezha.event.Result;
import io.wolverine.dns.client.listener.RecordListener;
import reactor.core.CoreSubscriber;

public abstract class AbstractDnsRecordSubscriber implements CoreSubscriber<ByteBuf>{
	private RecordListener recordListener;
	private AsyncResult asyncResult;
	public AbstractDnsRecordSubscriber(RecordListener recordListener, AsyncResult asyncResult) {
		this.recordListener = recordListener;
		this.asyncResult = asyncResult;
	}
	public RecordListener getRecordListener() {
		return recordListener;
	}
	protected abstract Object parseResult(ByteBuf t);
	protected abstract void notify(Object record);
	@Override
	public void onNext(ByteBuf t) {
		Object result = this.parseResult(t);
		this.notify(result);
		asyncResult.setResult(new Result<>(new ResultMsg<>(result)));
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
