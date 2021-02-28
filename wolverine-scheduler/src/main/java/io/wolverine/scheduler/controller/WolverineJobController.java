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
import io.wolverine.message.job.JobIdMsg;
import io.wolverine.message.job.QueryJobMsg;
import io.wolverine.message.job.SetJobModeMsg;
import io.wolverine.message.job.SubmitJobMsg;

@RestController
public class WolverineJobController {
	private @Autowired WolverineJobManager jobManager;
	@RequestMapping(value="/wolverine/scheduler/job/submitJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> submitJob(@RequestBody SubmitJobMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/stopJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> stopJob(@RequestBody JobIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/starJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> starJob(@RequestBody JobIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/deleteJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> deleteJob(@RequestBody JobIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/setJobMode", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> setJobMode(@RequestBody SetJobModeMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/queryJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> queryJob(@RequestBody QueryJobMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
}
