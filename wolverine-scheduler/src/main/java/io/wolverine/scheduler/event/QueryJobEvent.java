package io.wolverine.scheduler.event;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.IEvent;
import io.nezha.event.Result;
import io.wolverine.message.job.QueryJobMsg;
import io.wolverine.scheduler.rest.WolverineJobService;

public class QueryJobEvent implements IEvent {
	private QueryJobMsg msg;
	public QueryJobEvent(QueryJobMsg msg){
		this.msg = msg;
	}
	@Override
	public Result<ResultMsg> handleEvent() {
		return WolverineJobService.getService().queryJob(msg);
	}

}
