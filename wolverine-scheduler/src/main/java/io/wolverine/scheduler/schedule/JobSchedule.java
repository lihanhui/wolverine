package io.wolverine.scheduler.schedule;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;
import io.wolverine.common.job.ExecutorSpec;
import io.wolverine.common.job.ResourceSpec;
import io.wolverine.common.job.TaskSpec;
import io.wolverine.common.job.WolverineJobManager;
import io.wolverine.data.entity.ExecutorConfig;
import io.wolverine.data.entity.Job;
import io.wolverine.data.entity.dao.ExecutorConfigDao;
import io.wolverine.data.entity.dao.JobDao;
import io.wolverine.data.entity.dao.TaskDao;

@Component
public class JobSchedule extends Thread{
	static private Logger logger = LoggerFactory.getLogger(JobSchedule.class);
	private @Autowired WolverineJobManager jobManager;
	private @Autowired ExecutorConfigDao executorConfigDao ;
	private @Autowired TaskDao taskDao ;
	private @Autowired JobDao jobDao ;
	
	private ExecutorConfig executorConfig;
	private LinkedBlockingQueue<Job> jobQueue = new LinkedBlockingQueue<>();
	
	@PostConstruct
	private void postInit(){
		this.start();
	}
	public JobSchedule() {
		super();
		
	}
	
	@Scheduled(fixedRate = 30000)
	public void scheduleJob() {
		
	}
	private TaskSpec composeTaskSpec(Job job) {
		if(executorConfig == null){
			executorConfig = this.executorConfigDao.get("executor_config");
			if(executorConfig == null) return null;
		}
		ExecutorSpec executorSpec = new ExecutorSpec(executorConfig.getArchiveUri(), executorConfig.getCommand());
		ResourceSpec resourceSpec = new ResourceSpec(job.getCores(), job.getMem(), job.getDisk());
		TaskSpec spec = new TaskSpec(executorSpec, resourceSpec, job.getImageUri(), job.getEntryPoint(), 1);
		return spec;
	}
	private void run0() throws InterruptedException {
		Job job = null;
		while(( job = this.jobQueue.take()) != null){
			logger.info("got one job to run {}", job);
			this.jobManager.launchTasks(job.getJobId(), this.composeTaskSpec(job));
		}
	}
	public Boolean putJob(Job job) {
		return this.jobQueue.offer(job);
	}
	@Override
	public void run() {
		while(true) {
			try{
				run0();
			}catch(Throwable t) {
				logger.info("exception on running schedule task", t);
			}
		}
	}
}
