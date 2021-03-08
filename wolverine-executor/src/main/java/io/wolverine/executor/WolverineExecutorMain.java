package io.wolverine.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.MesosExecutorDriver;

import io.wolverine.common.executor.DefaultWolverineExecutor;
import io.wolverine.common.executor.WolverineExecutorListener;

public class WolverineExecutorMain 
{
    public static void main( String[] args )
    {
    	DefaultWolverineExecutor executor = new DefaultWolverineExecutor(null);
    	ExecutorDriver executorDriver = new MesosExecutorDriver(executor);
    	final WolverineExecutorListener taskManager = new SimpleWolverineTaskManager(executorDriver);
    	executor.setExecutorListener(taskManager);
    	executorDriver.run();
    }
}
