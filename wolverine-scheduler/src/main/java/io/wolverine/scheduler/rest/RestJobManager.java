package io.wolverine.scheduler.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import io.doraemon.restful.ResultMsg;
import io.nezha.event.Result;
import io.wolverine.message.job.JobIdMsg;
import io.wolverine.message.job.QueryJobMsg;
import io.wolverine.message.job.SetJobModeMsg;
import io.wolverine.message.job.SubmitJobMsg;
import io.wolverine.message.task.QueryTaskMsg;
import io.wolverine.message.task.SetTaskModeMsg;
import io.wolverine.message.task.SubmitTaskMsg;
import io.wolverine.message.task.TaskIdMsg;

public interface RestJobManager {
	public Result<ResultMsg> submitJob(SubmitJobMsg msg);
    public Result<ResultMsg> stopJob(JobIdMsg msg);
    public Result<ResultMsg> startJob(JobIdMsg msg);
    public Result<ResultMsg> deleteJob(JobIdMsg msg);
    public Result<ResultMsg> setJobMode(SetJobModeMsg msg);
    public Result<ResultMsg> queryJob(QueryJobMsg msg);
    
    public Result<ResultMsg> restartTask( TaskIdMsg msg);
    public Result<ResultMsg> killTask( TaskIdMsg msg);
    public Result<ResultMsg> startTask( TaskIdMsg msg);
    public Result<ResultMsg> submitTask( SubmitTaskMsg msg);
    public Result<ResultMsg> setTaskMode( SetTaskModeMsg msg);
    public Result<ResultMsg> queryTask( QueryTaskMsg msg);
}
