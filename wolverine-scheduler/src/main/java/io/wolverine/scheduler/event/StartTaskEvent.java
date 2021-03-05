package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.task.TaskIdMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class StartTaskEvent implements IEvent {
	private TaskIdMsg msg;
	public StartTaskEvent(TaskIdMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().startTask(msg);
	}

}
