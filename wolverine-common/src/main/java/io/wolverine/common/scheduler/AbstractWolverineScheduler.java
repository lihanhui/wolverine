package io.wolverine.common.scheduler;

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
	
	
	public AbstractWolverineScheduler(WolverineJobManager jobManager) {
		super();
		this.jobManager = jobManager;
	}

	public void registered(SchedulerDriver driver, FrameworkID frameworkId, MasterInfo masterInfo) {
		// TODO Auto-generated method stub
		
	}

	public void reregistered(SchedulerDriver driver, MasterInfo masterInfo) {
		// TODO Auto-generated method stub
		
	}

	public void resourceOffers(SchedulerDriver driver, List<Offer> offers) {
		// TODO Auto-generated method stub
		
	}

	public void offerRescinded(SchedulerDriver driver, OfferID offerId) {
		// TODO Auto-generated method stub
		
	}

	public void statusUpdate(SchedulerDriver driver, TaskStatus status) {
		// TODO Auto-generated method stub
		
	}

	public void frameworkMessage(SchedulerDriver driver, ExecutorID executorId, SlaveID slaveId, byte[] data) {
		// TODO Auto-generated method stub
		
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
