package io.wolverine.scheduler;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.MasterInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
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
	private String[] args;
	private CoordinatorService coordinatorService;
	private ConfigurableApplicationContext ctx ;
	private static String ZK_MESOS_MASTER = "/wolverine/master";
	public static String ZK_SCHEDULER_LEADER = "/wolverine/scheduler";
	public static String ZK_SCHEDULER_FRAMEWORK_ID = "/wolverine/scheduler/frameworkId";
	public WolverineSchedulerManager(String zks, String[] args, CoordinatorService coordinatorService) {
		this.zks = zks;
		this.args = args;
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
    			zks + ZK_MESOS_MASTER);
    	this.jobManager = new DefaultWolverineJobManager(schedulerDriver);
    	this.setJobManager(jobManager);
    	
	}
	private void join() {
		schedulerDriver.join();
	}
	private void start() {
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("io.wolverine.scheduler","io.distributed.unicorn.discovery");
    	//context.stop();
		schedulerDriver.start();
		ctx = SpringApplication.run(WolverineSchedulerMain.class, args);
    	System.out.println("the sd configuration: " + ((SomeComponent)ctx.getBean("someComponent")).getClient());
    	System.out.println("serviceDiscoveryClient: " + ((SomeComponent)ctx.getBean("someComponent")).getEnv());
	}
	public void reInitAndStart(String frameworkId) {
		if(schedulerDriver == null) {
			init(frameworkId);
		}
		start();
		join();
	}
	public void stop() {
		ctx.close();
		schedulerDriver.stop();
	}
	
	@Override
	public void registered(SchedulerDriver driver, FrameworkID frameworkId, MasterInfo masterInfo) {
		super.registered(driver, frameworkId, masterInfo);
		this.coordinatorService.write(ZK_SCHEDULER_FRAMEWORK_ID, frameworkId.getValue().getBytes());
	}
}
