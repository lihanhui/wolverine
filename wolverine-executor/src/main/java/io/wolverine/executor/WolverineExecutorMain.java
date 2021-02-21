package io.wolverine.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.MesosExecutorDriver;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.FrameworkInfo;

import io.wolverine.common.executor.DefaultWolverineExecutor;
import io.wolverine.common.job.DefaultWolverineJobManager;
import io.wolverine.common.job.TaskSpec;
import io.wolverine.common.task.DefaultWolverineTaskManager;

public class WolverineExecutorMain 
{
    public static void main( String[] args )
    {
    	DefaultWolverineExecutor executor = new DefaultWolverineExecutor(null);
    	ExecutorDriver executorDriver = new MesosExecutorDriver(executor);
    	final DefaultWolverineTaskManager taskManager = new DefaultWolverineTaskManager(executorDriver);
    	executor.setExecutorListener(taskManager);
    	executorDriver.run();
    }
}
