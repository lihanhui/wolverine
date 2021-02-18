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
		byte[] id = coordinatorService.read("/wolverine/scheduler/frameworkId");
		String frameworkId = id != null ? new String(id): null;
		this.manager.initOrJoin(frameworkId);
	}
	public void onLeaderLost() {
		this.manager.stop();
	}

}
