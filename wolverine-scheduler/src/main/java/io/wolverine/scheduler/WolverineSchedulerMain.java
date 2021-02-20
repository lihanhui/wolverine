package io.wolverine.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.distributed.unicorn.common.coordinator.CoordinatorService;
import io.distributed.unicorn.coordinator.zookeeper.SimpleCoordinatorService;
import io.distributed.unicorn.discovery.spring.context.annotation.EnableUnicornDiscovery;
import io.distributed.unicorn.discovery.spring.zookeeper.annotation.EnableZookeeperDiscovery;
import io.doraemon.daemon.AbstractDoraemonServer;

@EnableUnicornDiscovery
@EnableZookeeperDiscovery
@SpringBootApplication(scanBasePackages={"io.wolverine.scheduler"})
public class WolverineSchedulerMain extends AbstractDoraemonServer{
	public static void main( String[] args ) {
		System.setProperty("spring.application.name", "wolverine-scheduler");
		
    	String zks = args[0];
    	CoordinatorService coordinatorService 
    		= new SimpleCoordinatorService(zks.replace("zk://", ""), WolverineSchedulerManager.ZK_SCHEDULER_LEADER);
    	
    	WolverineSchedulerManager manager = new WolverineSchedulerManager(zks, args, coordinatorService);
    	
    	SchedulerLeaderStateListener listener = new SchedulerLeaderStateListener(manager, coordinatorService);
    	coordinatorService.addLeaderStateListener(listener);
    	coordinatorService.start();
    	
    	WolverineSchedulerMain main = new WolverineSchedulerMain();
    	main.start();
    }
    public void toPollService() {
    	Thread thread = new Thread(new Runnable() {
			public void run() {
				// component = 
				while(true) {
					try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
    	}); 
    	thread.start();
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
