package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.job.SetJobModeMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class SetJobModeEvent implements IEvent {
	private SetJobModeMsg msg;
	public SetJobModeEvent(SetJobModeMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().setJobMode(msg);
	}

}
