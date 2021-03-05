package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.job.JobIdMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class StopJobEvent implements IEvent {
	private JobIdMsg msg;
	public StopJobEvent(JobIdMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().stopJob(msg);
	}

}
