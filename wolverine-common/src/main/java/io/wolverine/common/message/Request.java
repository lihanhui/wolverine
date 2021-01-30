package io.wolverine.common.message;

public class Request {
	private org.apache.mesos.Protos.Request request;

	public Request(org.apache.mesos.Protos.Request request) {
		super();
		this.request = request;
	}
	public org.apache.mesos.Protos.Request getRequest() {
		return request;
	}

	public void setRequest(org.apache.mesos.Protos.Request request) {
		this.request = request;
	}
	
}
