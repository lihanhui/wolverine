package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.task.TaskIdMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class KillTaskEvent implements IEvent {
	private TaskIdMsg msg;
	public KillTaskEvent(TaskIdMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().killTask(msg);
	}

}
