package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *  Test class AnalysisTest.
 *  @author  shustovakv (mailto: shustovakv@mail.ru)
 *  @since 04.12.2018
 */
public class AnalysisTest {

    Analysis analysis = new Analysis();
    List<Analysis.User> previous = new ArrayList<>();
    List<Analysis.User> current = new ArrayList<>();

    @Before
    public void setUp() {
        Analysis.User first = new Analysis.User(1, "Ivan");
        Analysis.User second = new Analysis.User(2, "Vlad");
        this.previous.add(first);
        this.previous.add(second);
        this.current.addAll(previous);
    }

    @Test
    public void whenNewUserAddedToCollectionThenOneAdded() {
        current.add(new Analysis.User(3, "Vladimir"));
        Analysis.Info info = new Analysis.Info(1, 0, 0);
        assertThat(analysis.diff(previous, current), is(info));
    }

    @Test
    public void whenUserDeletedFromCollectionThenOneDeleted() {
        this.current.remove(1);
        Analysis.Info info = new Analysis.Info(0, 0, 1);
        assertThat(analysis.diff(previous, current), is(info));
    }

    @Test
    public void whenUserChangeThenOneChanges() {
        this.current.set(1, new Analysis.User(1, "IvanIvanov"));
        Analysis.Info info = new Analysis.Info(0, 1, 0);
        assertThat(analysis.diff(previous, current), is(info));
    }

    @Test
    public void whenMultipleChangesThenOneDeletedAndOneAddedAndOneChange() {
        this.current.set(1, new Analysis.User(1, "Petr"));
        this.current.remove(0);
        this.current.add(new Analysis.User(4, "Max"));
        Analysis.Info info = new Analysis.Info(1, 1, 1);
        assertThat(analysis.diff(previous, current), is(info));
    }
}