package io.wolverine.scheduler;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.SchedulerDriver;

import io.wolverine.common.job.DefaultWolverineJobManager;
import io.wolverine.common.job.TaskSpec;
import io.wolverine.common.scheduler.DefaultWolverineScheduler;

/**
 * Hello world!
 *
 */
public class WolverineSchedulerMain 
{
    public static void main( String[] args )
    {
    	FrameworkInfo.Builder b = FrameworkInfo.newBuilder();
    	b.setFailoverTimeout(7 * 24 * 60 * 60);
    	b.setUser("tmp");
    	b.setName("lihanhui");
    	FrameworkInfo framework = b.build();
    	DefaultWolverineScheduler scheduler = new DefaultWolverineScheduler(null);
    	SchedulerDriver schedulerDriver = new MesosSchedulerDriver(scheduler,
    			framework,
    			"172.26.150.204:5050");
    	final DefaultWolverineJobManager jobManager = new DefaultWolverineJobManager(schedulerDriver);
    	scheduler.setJobManager(jobManager);
    	schedulerDriver.start();
    	Thread thread = new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(10000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TaskSpec taskSpec = new TaskSpec(1, 100, 100, 1);
				jobManager.launchTasks("jobId", taskSpec);
			}
    	});
    	thread.start();
    	schedulerDriver.join();
        System.out.println( "Hello World!" );
    }
}
