package io.wolverine.scheduler.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.doraemon.restful.ResultMsg;

@RestController
public class WolverineTaskController {
	@RequestMapping(value="/wolverine/scheduler/task/restartTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> restartTask(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/killTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> killTask(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/startTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> startTask(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/task/queryTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> queryTask(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
}
