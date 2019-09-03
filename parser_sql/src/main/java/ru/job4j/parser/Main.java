package ru.job4j.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class Main.
 *
 * @author shustovakv
 * @since 12.11.2019
 */
public class Main {

    public static void main(String[] args) throws ParseException {

        ParserSQL parserSQL = new ParserSQL("https://www.sql.ru/forum/job-offers");
        System.out.println(parserSQL.listEntries(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
                .parse("11/10/2019 00:00:00.000")));
    }
}
