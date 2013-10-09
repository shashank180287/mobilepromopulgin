package com.mobile.tool.core.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.JobDetailBean;

import com.mobile.tool.core.util.ToolQuartzJob;

public class ToolApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
	
    @Autowired
    private Scheduler scheduler;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        try {
            ApplicationContext applicationContext = event.getApplicationContext();
            // load all quartz jobs
            loadAllToolQuartzJobs(applicationContext);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadAllToolQuartzJobs(ApplicationContext applicationContext) {
    	List<CronTriggerBean> cronTriggerBeans = this.loadCronTriggerBeans(applicationContext);
        this.scheduleJobs(cronTriggerBeans);
	}

	private List<CronTriggerBean> loadCronTriggerBeans(ApplicationContext applicationContext){
        Map<String, Object> quartzJobBeans = applicationContext.getBeansWithAnnotation(ToolQuartzJob.class);
        Set<String> beanNames = quartzJobBeans.keySet();
        List<CronTriggerBean> cronTriggerBeans = new ArrayList<CronTriggerBean>();
        for (String beanName : beanNames)
        {
            CronTriggerBean cronTriggerBean = null;
            Object object = quartzJobBeans.get(beanName);
            try {
                cronTriggerBean = this.buildCronTriggerBean(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(cronTriggerBean != null){
                cronTriggerBeans.add(cronTriggerBean);
            }
        }
        return cronTriggerBeans;
    }
    
    public CronTriggerBean buildCronTriggerBean(Object job) throws Exception {
        CronTriggerBean cronTriggerBean = null;
        ToolQuartzJob quartzJobAnnotation = AnnotationUtils.findAnnotation(job.getClass(), ToolQuartzJob.class);
        if(Job.class.isAssignableFrom(job.getClass()))
        {
            cronTriggerBean = new CronTriggerBean();
            cronTriggerBean.setCronExpression(quartzJobAnnotation.cronExp());                
            cronTriggerBean.setName(quartzJobAnnotation.name()+"_trigger");
            //cronTriggerBean.setGroup(quartzJobAnnotation.group());
            JobDetailBean jobDetail = new JobDetailBean();
            jobDetail.setName(quartzJobAnnotation.name());
            //jobDetail.setGroup(quartzJobAnnotation.group());
            jobDetail.setJobClass(job.getClass());
            cronTriggerBean.setJobDetail(jobDetail);            
        }
        else
        {
            throw new RuntimeException(job.getClass()+" doesn't implemented "+Job.class);
        }
        return cronTriggerBean;
    }
    
    protected void scheduleJobs(List<CronTriggerBean> cronTriggerBeans){
        for (CronTriggerBean cronTriggerBean : cronTriggerBeans) {
            JobDetail jobDetail = cronTriggerBean.getJobDetail();
            try {
                scheduler.scheduleJob(jobDetail, cronTriggerBean);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }            
        }
    }

}
