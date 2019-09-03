package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class ParserSQL.
 *
 * @author shustovakv
 * @since 22.08.2019
 */
public class ParserSQL {

    private String url;
    private int count = 0;
    private int currentPage = 0;

    public ParserSQL() {
    }

    public ParserSQL(String url) {
        this.url = url;
    }

    /**
     * Method for getting the list of vacancies
     * @param
     * @return
     */
    public List<Entry> listEntries(Date stopLine) {
        boolean continueSearching = true;
        List<Entry> vacancies = new ArrayList<>();
        while (continueSearching) {
            Document document = getPage(pagesLink(this.currentPage));
            Elements jobs = document.getElementsByTag("tr");
            int countJobs = jobs.size();
            do {
                for (Element job : jobs) {
                    Element link = job.selectFirst(".postslisttopic a:not(.newTopic):not(.pages)");
                    if (checkVacancy(link)) {
                        Element dateToParse = job.getElementsByTag("td").get(5).selectFirst(".altCol");
                        Date vacancyPublicationDate = parseDate(dateToParse);
                        if (vacancyPublicationDate.after(stopLine)) {
                            vacancies.add(new Entry(job.text(),
                                    vacancyBody(job.select("a").first().attr("href")),
                                    job.select("a").first().attr("href"),
                                    vacancyPublicationDate));
                        } else {
                            continueSearching = false;
                        }
                    }
                    countJobs--;
                }
            } while (countJobs > 0);
            this.currentPage++;
        }
        this.count = vacancies.size();
        return vacancies;
    }

    private Date parseDate(Element td) {
        Calendar cal = Calendar.getInstance();

        String str = td.text();
        String[] arrOfDateTime = str.split(",");
        String date = arrOfDateTime[0];

        if ("вчера".contains(date)) {
            cal.add(Calendar.DATE, -1);
        } else if (!"сегодня".contains(date)) {
            String[] arrOfDate = date.split(" ");
            cal.set(Calendar.DATE, Integer.parseInt(arrOfDate[0]));
            cal.set(Calendar.MONTH, convertMonth(arrOfDate[1]));
            cal.set(Calendar.YEAR, Integer.parseInt("20" + arrOfDate[2]));
        }

        String[] arrOfTime = arrOfDateTime[1].trim().split(":");
        cal.set(Calendar.HOUR, Integer.parseInt(arrOfTime[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(arrOfTime[1]));

        return cal.getTime();
    }

    private int convertMonth(String shortMonth) {
        int month;
        switch (shortMonth) {
            case ("янв") : month = 0; break;
            case ("фев") : month = 1; break;
            case ("мар") : month = 2; break;
            case ("апр") : month = 3; break;
            case ("май") : month = 4; break;
            case ("июн") : month = 5; break;
            case ("июл") : month = 6; break;
            case ("авг") : month = 7; break;
            case ("сен") : month = 8; break;
            case ("окт") : month = 9; break;
            case ("ноя") : month = 10; break;
            case ("дек") : month = 11; break;
            default: month = -1;
        } return month;
    }

    /**
     * Method for getting job descriptions. For example required skills etc.
     * @param link to job in text format
     * @return description of vacancy in List format
     */
    private String vacancyBody(String link) {
        Document document = getPage(link);
        Element msg = document.selectFirst(".msgTable");
        String vacancyBody = "";
        Elements elements = msg.select(".msgBody");
        if (elements.size() > 1) {
            vacancyBody = elements.get(1).text();
        }
        return vacancyBody;
    }

    /**
     * Method for checking vacancies in Java
     * @param link to job
     * @return it is Java or not
     */
    private boolean checkVacancy(Element link) {
        boolean isJava = false;
        if (link != null) {
            String string = link.toString();
            isJava = !(string.contains("JavaScript") || string.contains("Java Script")) && string.contains("Java");
        }
        return isJava;
    }

    /**
     * Method that appends the end of the link
     * @param page the current page
     * @return the end of the link for example if the page is first return "", if the page is next return "/number of page"
     */
    public String pagesLink(int page) {
        return page > 1 ? this.url + "/" + page : this.url;
    }

    /**
     * Method which get html page
     */
    protected Document getPage(String link) {
        Document document = null;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * Counter of the number of vacancies found
     * @return count of vacancies
     */
    public int countOfNewVacancies() {
        return count;
    }
}
