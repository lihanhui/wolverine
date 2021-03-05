package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.task.TaskIdMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class RestartTaskEvent implements IEvent {
	private TaskIdMsg msg;
	public RestartTaskEvent(TaskIdMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().restartTask(msg);
	}

}
