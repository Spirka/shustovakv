package ru.job4j.parser;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserSQLTest {

    private Document init(String name) throws IOException {
        String html = IOUtils.toString(Objects.requireNonNull(
                ParserSQLTest.class.getClassLoader()
                        .getResourceAsStream(name)),
                "windows-1251");
        return Jsoup.parse(html);
    }

    private Document loadVacancy(String path) throws IOException {
        String vacancy = IOUtils.toString(Objects.requireNonNull(
                ParserSQLTest.class.getClassLoader()
                        .getResourceAsStream(path)),
                "windows-1251");
        return Jsoup.parse(vacancy);
    }

    /**@Test
    public void whenParserMakesListEntriesThenReturnListOfVacancies() throws IOException, ParseException {
        Map<String, String> map = new HashMap<>();
        map.put("https://www.sql.ru/forum/1314951/sistemnyy-programmist-c-java-ot-200tr", "vacancy1.html");
        map.put("https://www.sql.ru/forum/1317131/java-razrabotchik-moskva-smolenskaya-60-200-tys", "vacancy2.html");
        map.put("https://www.sql.ru/forum/1317074/java-razrabotchik-moskva-novosibirsk-penza-vozmozhna-udalenka", "vacancy3.html");
        ParserSQL parserSQL = new ParserSQL() {

            @Override
            public Document init() throws IOException {
                return ParserSQLTest.this.init("test_vacancies_SQL.html");
            }

            @Override
            public Document getPage(String link) throws IOException {
                return loadVacancy(map.get(link));
            }
        };
        parserSQL.listEntries(ParserSQLTest.this.init("test_vacancies_SQL.html"),
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
                        .parse("09/09/2019 12:41:00.000"));
        assertThat(parserSQL.countOfNewVacancies(), is(3));
    }*/

    /**@Test
    public void whenParserFindVacanciesThenReturnCount() throws ParseException, IOException {
        ParserSQL parserSQL = new ParserSQL() {

            @Override
            public Document init() throws IOException {
                return ParserSQLTest.this.init("vacancy4.html");
            }

        };
        ParserSQLTest.this.init("vacancy4.html"),
        parserSQL.listEntries(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
                        .parse("21/10/2019 15:41:00.000"));
        assertThat(parserSQL.countOfNewVacancies(), is(7));
    }

    @Test
    public void whenParserFoundOnlyOneVacancy() throws IOException, ParseException {
        ParserSQL parserSQL = new ParserSQL() {
            @Override
            public Document init() throws IOException {
                return ParserSQLTest.this.init("javaDep.html");
            }
        };
        parserSQL.listEntries(ParserSQLTest.this.init("javaDep.html"),
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
                        .parse("11/10/2019 00:00:00.000"));
        assertThat(parserSQL.countOfNewVacancies(), is(3));
    }*/
}