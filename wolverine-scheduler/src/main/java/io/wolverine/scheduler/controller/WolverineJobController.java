package io.wolverine.scheduler.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.doraemon.restful.ResultMsg;

@RestController
public class WolverineJobController {
	@RequestMapping(value="/wolverine/scheduler/job/submitJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> submitJob(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/stopJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> stopJob(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/starJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> starJob(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/deleteJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> deleteJob(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/queryJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> queryJob(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
}
