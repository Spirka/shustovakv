package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Class ParserJob.
 *
 * @author shustovakv
 * @since 27.08.2019
 */
public class ParserJob implements Job {

    private final static Logger LOG = LogManager.getLogger(ParserJob.class.getName());
    private Properties properties;

    @Override
    public void execute(JobExecutionContext context) {
        LOG.info(String.format("START, %s", new Date()));
        JobDataMap data = context.getJobDetail().getJobDataMap();
        this.properties = (Properties) data.get("properties");
        Date previousFireTime = context.getPreviousFireTime();
        ParserSQL parser = new ParserSQL("https://www.sql.ru/forum/job-offers");
        VacancyStore vacancyStore = new VacancyStore(this.init());

        if (previousFireTime == null) {
            List<Entry> vacancies = parser.listEntries(this.firstDayOfYear());
            vacancyStore.add(vacancies);
        } else {
            List<Entry> vacancies = parser.listEntries(previousFireTime);
            vacancyStore.add(vacancies);
        }
        LOG.info(String.format("There are %s vacancies found", parser.countOfNewVacancies()));
        LOG.info(String.format("END, %s", new Date()));
    }

    private Connection init() {
        try {
            return DriverManager.getConnection(
                    this.properties.getProperty("url"),
                    this.properties.getProperty("username"),
                    this.properties.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private Date firstDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
