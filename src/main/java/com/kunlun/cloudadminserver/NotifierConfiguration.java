package com.kunlun.cloudadminserver;


import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import de.codecentric.boot.admin.notify.filter.FilteringNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * 下发通知
 *
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/28.
 */
@Configuration
//@EnableScheduling
public class NotifierConfiguration {

    private static final Logger LOGGER  = LoggerFactory.getLogger(NotifierConfiguration.class);

    @Autowired
    private Notifier delegate;

    private String[] reminderStatus = {"DOWN"};

    @Bean
    public FilteringNotifier filteringNotifier(){
        return new FilteringNotifier(delegate);
    }


    @Bean
    @Primary
    public RemindingNotifier remindingNotifier() {
        LOGGER.info("执行Notify  通知任务############");
        RemindingNotifier remindingNotifier = new RemindingNotifier(filteringNotifier());
        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMillis(3));
        remindingNotifier.setReminderStatuses(reminderStatus);
        return remindingNotifier;
    }

    @Scheduled(fixedRate = 60_000L)
    public void remind() {
        remindingNotifier().sendReminders();
    }
}
