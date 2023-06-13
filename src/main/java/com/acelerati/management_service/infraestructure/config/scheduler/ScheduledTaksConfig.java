package com.acelerati.management_service.infraestructure.config.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
//this class is used to configure the scheduler
public class ScheduledTaksConfig implements SchedulingConfigurer {

    //this method is used to configure the scheduler
    //use the ThreadPoolTaskScheduler to configure the scheduler
    // we can use the @Scheduled annotation to configure the scheduler
    // ths config es for execute threads in  async form, methos with notattion @Schedule
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //num threads for task or methods with @scheduler
        scheduler.setPoolSize(10);
        scheduler.initialize();
        taskRegistrar.setTaskScheduler(scheduler);

    }
}
