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
    public DeferredResult<ResultMsg> submitUpdate(/*@RequestBody SubmitUpdateMsg submitUpdateMsg,*/
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		dr.setResult(ResultMsg.ok());
		return dr;
	}
}
