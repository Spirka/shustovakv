package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Class ParsingScheduler.
 *
 * @author shustovakv
 * @since 23.08.2019
 */
public class ParsingScheduler {

    private final static Logger LOG = LogManager.getLogger(ParsingScheduler.class.getName());

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            InputStream in = ParserSQL.class.getClassLoader().getResourceAsStream(args[3]);
            properties.load(Objects.requireNonNull(in));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        if (!properties.isEmpty()) {
            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();

                JobDetail jobSQL = newJob(ParserJob.class)
                        .withIdentity("jobSQL", "group1")
                        .build();

                jobSQL.getJobDataMap().put("properties", properties);

                CronTrigger trigger = newTrigger()
                        .withIdentity("TriggerSQL", "group1")
                        .withSchedule(CronScheduleBuilder.cronSchedule(properties.getProperty("cron.time")))
                        .build();

                scheduler.scheduleJob(jobSQL, trigger);

            } catch (SchedulerException e) {
                LOG.error(e.getMessage() + e);
            }
            LOG.info("Triggered...");
        }
    }
}
