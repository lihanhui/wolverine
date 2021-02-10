package io.wolverine.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.MesosExecutorDriver;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.FrameworkInfo;

import io.wolverine.common.executor.DefaultWolverineExecutor;
import io.wolverine.common.job.DefaultWolverineJobManager;
import io.wolverine.common.job.TaskSpec;
import io.wolverine.common.task.DefaultWolverineTaskManager;

/**
 * Hello world!
 *
 */
public class WolverineExecutorMain 
{
    public static void main( String[] args )
    {
    	FrameworkInfo.Builder b = FrameworkInfo.newBuilder().setId(FrameworkID.newBuilder().setValue("")).
    	setFailoverTimeout(7 * 24 * 60 * 60).
    	setUser("tmp").
    	setName("lihanhui");
    	DefaultWolverineExecutor executor = new DefaultWolverineExecutor(null);
    	ExecutorDriver executorDriver = new MesosExecutorDriver(executor);
    	//b.build(),
		//"172.26.150.204:5050"
    	final DefaultWolverineTaskManager taskManager = new DefaultWolverineTaskManager(executorDriver);
    	executor.setTaskManager(taskManager);
    	executorDriver.start();
    	Thread thread = new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(10000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	});
    	thread.start();
    	executorDriver.join();
        System.out.println( "Hello World!" );
    }
}
