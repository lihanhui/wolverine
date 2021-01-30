package io.wolverine.common.job;

import java.util.List;

import io.wolverine.common.message.Offer;
import io.wolverine.common.message.TaskStatus;

public interface WolverineJob {
	public void offersReceived(List<Offer> offers) ;
	public void offerRescinded(String offerId);
	public void statusUpdated(TaskStatus status);
}
