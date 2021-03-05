package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.job.JobIdMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class StartJobEvent implements IEvent {
	private JobIdMsg msg;
	public StartJobEvent(JobIdMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().startJob(msg);
	}

}
