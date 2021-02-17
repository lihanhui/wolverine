package io.wolverine.scheduler;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.SchedulerDriver;

import io.wolverine.common.job.DefaultWolverineJobManager;
import io.wolverine.common.job.ExecutorSpec;
import io.wolverine.common.job.ResourceSpec;
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
    	b.setUser("");
    	b.setName("lihanhui");
    	b.setId(FrameworkID.newBuilder().setValue("fa59b30a-34a8-49ff-92df-b52a53b704fb-0000"));
    	FrameworkInfo framework = b.build();
    	DefaultWolverineScheduler scheduler = new DefaultWolverineScheduler(null);
    	SchedulerDriver schedulerDriver = new MesosSchedulerDriver(scheduler,
    			framework,
    			"zk://localhost:2181/wolverine/master");
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
				ExecutorSpec executorSpec = new ExecutorSpec("http://172.26.150.204/wolverine-executor.tar.gz", "java -jar wolverine-executor-1.0-jar-with-dependencies.jar");
				ResourceSpec resourceSpec = new ResourceSpec(1, 100, 100);
				TaskSpec taskSpec = new TaskSpec(executorSpec, resourceSpec, "", "", 1);
				jobManager.launchTasks("jobId", taskSpec);
			}
    	});
    	thread.start();
    	schedulerDriver.join();
        System.out.println( "Hello World!" );
    }
}
