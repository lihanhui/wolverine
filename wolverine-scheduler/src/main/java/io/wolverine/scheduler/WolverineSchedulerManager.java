package io.wolverine.scheduler;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.MasterInfo;
import org.apache.mesos.SchedulerDriver;

import io.distributed.unicorn.common.coordinator.CoordinatorService;
import io.wolverine.common.job.DefaultWolverineJobManager;
import io.wolverine.common.job.WolverineJobManager;
import io.wolverine.common.scheduler.DefaultWolverineScheduler;
import io.wolverine.common.scheduler.WolverineScheduler;

public class WolverineSchedulerManager extends DefaultWolverineScheduler{
	private SchedulerDriver schedulerDriver = null;
	private DefaultWolverineJobManager jobManager = null;
	private String zks = null;
	private CoordinatorService coordinatorService;
	private static String zkSchedulerPath = "/wolverine/scheduler";
	public WolverineSchedulerManager(String zks, CoordinatorService coordinatorService) {
		this.zks = zks;
		this.coordinatorService = coordinatorService;
	}
	private void init(String frameworkId) {
		FrameworkInfo.Builder b = FrameworkInfo.newBuilder();
    	b.setFailoverTimeout(7 * 24 * 60 * 60);
    	b.setUser("");
    	b.setName("lihanhui@gmail.com");
    	if(frameworkId != null) {
    		b.setId(FrameworkID.newBuilder().setValue(frameworkId));
    	}
    	FrameworkInfo framework = b.build();
    	//DefaultWolverineScheduler scheduler = new DefaultWolverineScheduler(null);
    	this.schedulerDriver = new MesosSchedulerDriver(this,
    			framework,
    			zks+zkSchedulerPath);
    	this.jobManager = new DefaultWolverineJobManager(schedulerDriver);
    	this.setJobManager(jobManager);
    	schedulerDriver.start();
	}
	private void join() {
		schedulerDriver.join();
	}
	public void initOrJoin(String frameworkId) {
		if(schedulerDriver == null) {
			init(frameworkId);
		}
		join();
	}
	public void stop() {
		schedulerDriver.stop();
	}
	
	@Override
	public void registered(SchedulerDriver driver, FrameworkID frameworkId, MasterInfo masterInfo) {
		super.registered(driver, frameworkId, masterInfo);
		this.coordinatorService.write(zkSchedulerPath+"/frameworkId", frameworkId.getValue().getBytes());
	}
}
