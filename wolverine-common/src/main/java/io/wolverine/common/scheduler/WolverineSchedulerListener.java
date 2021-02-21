package io.wolverine.common.scheduler;

import java.util.List;

import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.TaskStatus;

public interface WolverineSchedulerListener {
	void resourceOffers(List<Offer> offers) ;
	void offerRescinded(String offerId);
	void statusUpdate(TaskStatus status);
	void frameworkMessage(byte[] data);
}
