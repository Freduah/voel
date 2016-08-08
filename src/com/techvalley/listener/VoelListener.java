package com.techvalley.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.techvalley.job.VoelMTSmsJob;



public class VoelListener implements ServletContextListener {
	
	Scheduler voelScheduler = null;

	@Override
	public void contextDestroyed(ServletContextEvent servletContext) {
		try{
			voelScheduler.shutdown();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContext) {
		try{
			
			 JobDetail VoelSchedulerJob = JobBuilder.newJob(VoelMTSmsJob.class)
	            		.withIdentity("VoelSchedulerCronJob", "Group").build();
			 
			 Trigger VoelSchedulerTrigger = TriggerBuilder.newTrigger()
	            		.withIdentity("VoelSchedulerCronJobTrigger", "Group")
	            		.withSchedule(
	            				SimpleScheduleBuilder.simpleSchedule()
	            				.withIntervalInSeconds(10).repeatForever()
	            				)
	            		.build();
			 
			// Setup the Job and Trigger with Scheduler & schedule jobs
			 voelScheduler = new StdSchedulerFactory().getScheduler();
			 voelScheduler.start();             
			 voelScheduler.scheduleJob(VoelSchedulerJob, VoelSchedulerTrigger); 		 
			 
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
