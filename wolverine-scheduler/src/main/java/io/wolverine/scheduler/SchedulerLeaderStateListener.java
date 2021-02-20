package io.wolverine.scheduler;

import io.distributed.unicorn.common.coordinator.CoordinatorService;
import io.distributed.unicorn.common.coordinator.LeaderStateListener;

public class SchedulerLeaderStateListener implements LeaderStateListener{
	private WolverineSchedulerManager manager;
	private CoordinatorService coordinatorService;//.read(zkSchedulerPath + "/frameworkId");
	
	public SchedulerLeaderStateListener(WolverineSchedulerManager manager, CoordinatorService coordinatorService) {
		this.manager = manager;
		this.coordinatorService = coordinatorService;
	}
	public void onLeaderTaken() {
		System.out.println("leadership gotten");
		byte[] id = coordinatorService.read(WolverineSchedulerManager.ZK_SCHEDULER_FRAMEWORK_ID);
		String frameworkId = id != null ? new String(id): null;
		this.manager.reInitAndStart(frameworkId);
	}
	public void onLeaderLost() {
		this.manager.stop();
	}

}
