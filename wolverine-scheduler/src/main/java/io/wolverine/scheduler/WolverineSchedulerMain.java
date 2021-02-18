package io.wolverine.scheduler;

import io.distributed.unicorn.common.coordinator.CoordinatorService;
import io.distributed.unicorn.coordinator.zookeeper.SimpleCoordinatorService;
import io.doraemon.daemon.AbstractDoraemonServer;

public class WolverineSchedulerMain extends AbstractDoraemonServer{
    public static void main( String[] args ) {
    	String zks = args[0];
    	System.out.println(args[0]);
    	
    	CoordinatorService coordinatorService 
    		= new SimpleCoordinatorService(zks.replace("zk://", ""), "/wolverine/scheduler");
    	
    	WolverineSchedulerManager manager = new WolverineSchedulerManager(zks, coordinatorService);
    	
    	SchedulerLeaderStateListener listener = new SchedulerLeaderStateListener(manager, coordinatorService);
    	coordinatorService.addLeaderStateListener(listener);
    	System.out.println("start coordinator service");
    	coordinatorService.start();
    	new WolverineSchedulerMain().start();
    }
    private void startTask() {
    	/*Thread thread = new Thread(new Runnable() {
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
    	thread.start();//*/
    }
}
