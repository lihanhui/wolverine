package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.task.QueryTaskMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class QueryTaskEvent implements IEvent {
	private QueryTaskMsg msg;
	public QueryTaskEvent(QueryTaskMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().queryTask(msg);
	}

}
