package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.task.SubmitTaskMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class SubmitTaskEvent implements IEvent {
	private SubmitTaskMsg msg;
	public SubmitTaskEvent(SubmitTaskMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().submitTask(msg);
	}

}
