package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.task.SetTaskModeMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class SetTaskModeEvent implements IEvent {
	private SetTaskModeMsg msg;
	public SetTaskModeEvent(SetTaskModeMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().setTaskMode(msg);
	}

}
