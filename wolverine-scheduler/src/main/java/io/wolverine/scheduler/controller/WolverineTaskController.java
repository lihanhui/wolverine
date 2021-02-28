package io.wolverine.scheduler.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.doraemon.restful.ResultMsg;
import io.wolverine.common.job.WolverineJobManager;
import io.wolverine.message.task.QueryTaskMsg;
import io.wolverine.message.task.SetTaskModeMsg;
import io.wolverine.message.task.SubmitTaskMsg;
import io.wolverine.message.task.TaskIdMsg;

@RestController
public class WolverineTaskController {
	private @Autowired WolverineJobManager jobManager;
	@RequestMapping(value="/wolverine/scheduler/task/restartTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> restartTask(@RequestBody TaskIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/killTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> killTask(@RequestBody TaskIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/startTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> startTask(@RequestBody TaskIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/submitTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> submitTask(@RequestBody SubmitTaskMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/setTaskMode", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> setTaskMode(@RequestBody SetTaskModeMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/queryTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> queryTask(@RequestBody QueryTaskMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
}
