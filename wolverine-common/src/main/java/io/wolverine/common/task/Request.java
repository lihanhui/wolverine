package io.wolverine.common.task;

public class Request {
	private org.apache.mesos.Protos.Request request;

	public Request(org.apache.mesos.Protos.Request request) {
		super();
		this.request = request;
	}
	
}
