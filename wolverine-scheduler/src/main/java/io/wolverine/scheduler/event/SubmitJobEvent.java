package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.job.SubmitJobMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class SubmitJobEvent implements IEvent {
	private SubmitJobMsg msg;
	public SubmitJobEvent(SubmitJobMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().submitJob(msg);
	}

}
