package io.wolverine.common.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.apache.mesos.Protos.ExecutorID;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.MasterInfo;
import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.Protos.SlaveID;
import org.apache.mesos.Protos.TaskStatus;
import org.apache.mesos.SchedulerDriver;

import io.wolverine.common.job.WolverineJobManager;

public class AbstractWolverineScheduler implements WolverineScheduler{
	private WolverineJobManager jobManager;
	private SchedulerEnv schedulerEnv;
	
	public AbstractWolverineScheduler(WolverineJobManager jobManager) {
		super();
		this.jobManager = jobManager;
		this.schedulerEnv = new SchedulerEnv();
	}

	public void registered(SchedulerDriver driver, FrameworkID frameworkId, MasterInfo masterInfo) {
		schedulerEnv.setFrameworkId(frameworkId);
		schedulerEnv.setMasterInfo(masterInfo);
	}

	public void reregistered(SchedulerDriver driver, MasterInfo masterInfo) {
		schedulerEnv.setMasterInfo(masterInfo);
	}

	public void resourceOffers(SchedulerDriver driver, List<Offer> offers) {
		List<io.wolverine.common.task.Offer> list = new ArrayList<>();
		offers.forEach( o -> list.add(new io.wolverine.common.task.Offer(o)));
		this.jobManager.resourceOffers(list);
	}

	public void offerRescinded(SchedulerDriver driver, OfferID offerId) {
		this.jobManager.offerRescinded(offerId.getValue());
	}

	public void statusUpdate(SchedulerDriver driver, TaskStatus status) {
		this.jobManager.statusUpdate(new io.wolverine.common.task.TaskStatus(status));
	}

	public void frameworkMessage(SchedulerDriver driver, ExecutorID executorId, SlaveID slaveId, byte[] data) {
		this.jobManager.frameworkMessage(data);
	}

	public void disconnected(SchedulerDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void slaveLost(SchedulerDriver driver, SlaveID slaveId) {
		// TODO Auto-generated method stub
		
	}

	public void executorLost(SchedulerDriver driver, ExecutorID executorId, SlaveID slaveId, int status) {
		// TODO Auto-generated method stub
		
	}

	public void error(SchedulerDriver driver, String message) {
		// TODO Auto-generated method stub
		
	}

}
