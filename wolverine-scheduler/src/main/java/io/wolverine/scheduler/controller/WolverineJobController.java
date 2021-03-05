package io.wolverine.scheduler.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.doraemon.restful.ResultMsg;
import io.nezha.channel.ChannelFuture;
import io.nezha.channel.ChannelFutureListener;
import io.nezha.event.Result;
import io.nezha.schedule.IScheduler;
import io.nezha.schedule.SchedulerGroup;
import io.wolverine.message.job.JobIdMsg;
import io.wolverine.message.job.QueryJobMsg;
import io.wolverine.message.job.SetJobModeMsg;
import io.wolverine.message.job.SubmitJobMsg;
import io.wolverine.scheduler.event.DeleteJobEvent;
import io.wolverine.scheduler.event.QueryJobEvent;
import io.wolverine.scheduler.event.SetJobModeEvent;
import io.wolverine.scheduler.event.StartJobEvent;
import io.wolverine.scheduler.event.StopJobEvent;
import io.wolverine.scheduler.event.SubmitJobEvent;

@RestController
public class WolverineJobController {
	private IScheduler scheduler;
	public WolverineJobController(){
    	scheduler = SchedulerGroup.getScheduler();
    }
	@RequestMapping(value="/wolverine/scheduler/job/submitJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> submitJob(@RequestBody SubmitJobMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new SubmitJobEvent(msg));
		future.addListener(new ChannelFutureListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					//logger.debug("get result {}",future.get());
					Result<ResultMsg> result = (Result<ResultMsg>)future.get();
					dr.setResult(result.getValue());
				}else{
					//logger.info("unknown error in server cause ", future.cause());
					dr.setResult(new ResultMsg(-1500, "unknown error in server", System.currentTimeMillis(), null));
				}
				
			}});
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/stopJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> stopJob(@RequestBody JobIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new StopJobEvent(msg));
		future.addListener(new ChannelFutureListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					//logger.debug("get result {}",future.get());
					Result<ResultMsg> result = (Result<ResultMsg>)future.get();
					dr.setResult(result.getValue());
				}else{
					//logger.info("unknown error in server cause ", future.cause());
					dr.setResult(new ResultMsg(-1500, "unknown error in server", System.currentTimeMillis(), null));
				}
				
			}});
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/startJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> startJob(@RequestBody JobIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new StartJobEvent(msg));
		future.addListener(new ChannelFutureListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					//logger.debug("get result {}",future.get());
					Result<ResultMsg> result = (Result<ResultMsg>)future.get();
					dr.setResult(result.getValue());
				}else{
					//logger.info("unknown error in server cause ", future.cause());
					dr.setResult(new ResultMsg(-1500, "unknown error in server", System.currentTimeMillis(), null));
				}
				
			}});
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/deleteJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> deleteJob(@RequestBody JobIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new DeleteJobEvent(msg));
		future.addListener(new ChannelFutureListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					//logger.debug("get result {}",future.get());
					Result<ResultMsg> result = (Result<ResultMsg>)future.get();
					dr.setResult(result.getValue());
				}else{
					//logger.info("unknown error in server cause ", future.cause());
					dr.setResult(new ResultMsg(-1500, "unknown error in server", System.currentTimeMillis(), null));
				}
				
			}});
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/setJobMode", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> setJobMode(@RequestBody SetJobModeMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new SetJobModeEvent(msg));
		future.addListener(new ChannelFutureListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					//logger.debug("get result {}",future.get());
					Result<ResultMsg> result = (Result<ResultMsg>)future.get();
					dr.setResult(result.getValue());
				}else{
					//logger.info("unknown error in server cause ", future.cause());
					dr.setResult(new ResultMsg(-1500, "unknown error in server", System.currentTimeMillis(), null));
				}
				
			}});
		return dr;
	}
	@RequestMapping(value="/wolverine/scheduler/job/queryJob", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> queryJob(@RequestBody QueryJobMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new QueryJobEvent(msg));
		future.addListener(new ChannelFutureListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					//logger.debug("get result {}",future.get());
					Result<ResultMsg> result = (Result<ResultMsg>)future.get();
					dr.setResult(result.getValue());
				}else{
					//logger.info("unknown error in server cause ", future.cause());
					dr.setResult(new ResultMsg(-1500, "unknown error in server", System.currentTimeMillis(), null));
				}
				
			}});
		return dr;
	}
}
