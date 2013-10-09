package com.mobile.tool.inventory.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mobile.tool.core.util.ToolQuartzJob;

@ToolQuartzJob(name = "InventorySystemDataSyncJob", cronExp = "0 0 1 * * ?")
public class InventorySystemDataSyncJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("InventorySystemDataSyncJob: Not Implemented till now");
	}
}
