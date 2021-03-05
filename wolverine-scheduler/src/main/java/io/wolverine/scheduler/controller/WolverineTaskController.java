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
import io.wolverine.message.task.QueryTaskMsg;
import io.wolverine.message.task.SetTaskModeMsg;
import io.wolverine.message.task.SubmitTaskMsg;
import io.wolverine.message.task.TaskIdMsg;
import io.wolverine.scheduler.event.KillTaskEvent;
import io.wolverine.scheduler.event.QueryTaskEvent;
import io.wolverine.scheduler.event.RestartTaskEvent;
import io.wolverine.scheduler.event.SetTaskModeEvent;
import io.wolverine.scheduler.event.StartTaskEvent;
import io.wolverine.scheduler.event.SubmitTaskEvent;

@RestController
public class WolverineTaskController {
	private IScheduler scheduler;
	public WolverineTaskController(){
    	scheduler = SchedulerGroup.getScheduler();
    }
	@RequestMapping(value="/wolverine/scheduler/task/restartTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> restartTask(@RequestBody TaskIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new RestartTaskEvent(msg));
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
	@RequestMapping(value="/wolverine/scheduler/task/killTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> killTask(@RequestBody TaskIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new KillTaskEvent(msg));
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
	@RequestMapping(value="/wolverine/scheduler/task/startTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> startTask(@RequestBody TaskIdMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new StartTaskEvent(msg));
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
	@RequestMapping(value="/wolverine/scheduler/task/submitTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> submitTask(@RequestBody SubmitTaskMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new SubmitTaskEvent(msg));
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
	@RequestMapping(value="/wolverine/scheduler/task/setTaskMode", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> setTaskMode(@RequestBody SetTaskModeMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new SetTaskModeEvent(msg));
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
	@RequestMapping(value="/wolverine/scheduler/task/queryTask", method=RequestMethod.POST)
    public DeferredResult<ResultMsg> queryTask(@RequestBody QueryTaskMsg msg,
    		HttpServletRequest request) {
		DeferredResult<ResultMsg> dr = new DeferredResult<ResultMsg>();
		ChannelFuture future = this.scheduler.submitEvent("", new QueryTaskEvent(msg));
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
