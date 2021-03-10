package io.wolverine.scheduler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.doraemon.restful.ResultMsg;
import io.doraemon.uuid.UUID;
import io.nezha.event.Result;
import io.wolverine.common.job.WolverineJobManager;
import io.wolverine.data.entity.Job;
import io.wolverine.data.entity.dao.JobDao;
import io.wolverine.data.entity.dao.TaskDao;
import io.wolverine.message.job.JobIdMsg;
import io.wolverine.message.job.QueryJobMsg;
import io.wolverine.message.job.SetJobModeMsg;
import io.wolverine.message.job.SubmitJobMsg;
import io.wolverine.message.task.QueryTaskMsg;
import io.wolverine.message.task.SetTaskModeMsg;
import io.wolverine.message.task.SubmitTaskMsg;
import io.wolverine.message.task.TaskIdMsg;
import io.wolverine.scheduler.schedule.JobSchedule;

@Component("restJobManager")
public class WolverineJobService 
	implements RestJobManager{
	private @Autowired JobDao jobDao;
	private @Autowired TaskDao taskDao;
	private @Autowired WolverineJobManager wolverineJobManager;
	private @Autowired JobSchedule jobSchedule;
	private static WolverineJobService service;
	public static WolverineJobService getService() {
		return service;
	}
	
	public  WolverineJobService() {
		WolverineJobService.service = this;
	}
	private Job transferSubmitJobMsg2Entity(SubmitJobMsg msg) {
		long now = System.currentTimeMillis();
		Job job = new Job();
		job.setJobName(msg.getJobName());
		job.setCores(msg.getCores());
		job.setDisk(msg.getDisk());
		//job.setEntryPoint(msg.getEntryPoint());
		job.setImageUri(msg.getImageUri());
		job.setMem(msg.getMem());
		job.setMode(msg.getMode());
		job.setOptions(msg.getOptions());
		job.setTarget(msg.getTarget());
		job.setTasks(msg.getTasks());
		job.setTaskType(msg.getTaskType());
		job.setCreateDate(now);
		job.setUpdateDate(now);
		return job;
	}
	@Override
	public Result<ResultMsg> submitJob(SubmitJobMsg msg) {
		Job job = transferSubmitJobMsg2Entity(msg);
		job.setJobId(UUID.uuid());
		this.jobDao.save(job);
		this.jobSchedule.putJob(job);
		return new Result<>(ResultMsg.ok());
	}

	@Override
	public Result<ResultMsg> stopJob(JobIdMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> startJob(JobIdMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> deleteJob(JobIdMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> setJobMode(SetJobModeMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> queryJob(QueryJobMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> restartTask(TaskIdMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> killTask(TaskIdMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> startTask(TaskIdMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> submitTask(SubmitTaskMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> setTaskMode(SetTaskModeMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ResultMsg> queryTask(QueryTaskMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
