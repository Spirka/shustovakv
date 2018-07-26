package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Class DetectionOfCycleTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 26.07.2018
 */
public class DetectionOfCycleTest {

    DetectionOfCycle testList = new DetectionOfCycle();
    DetectionOfCycle.Node<Integer>[] arrayNode = new DetectionOfCycle.Node[5];

    @Before
    public void setUp() {
        arrayNode[0] = new DetectionOfCycle.Node<>(1);
        arrayNode[1] = new DetectionOfCycle.Node<>(2);
        arrayNode[2] = new DetectionOfCycle.Node<>(3);
        arrayNode[3] = new DetectionOfCycle.Node<>(4);
        arrayNode[4] = new DetectionOfCycle.Node<>(5);
    }

    @Test
    public void whenTestNormalQueueThanHasNotCircle() {
        arrayNode[0].next = arrayNode[1];
        arrayNode[1].next = arrayNode[2];
        arrayNode[2].next = arrayNode[3];
        arrayNode[3].next = arrayNode[4];
        assertThat(testList.hasCycle(arrayNode[0]), is(false));
    }

    @Test
    public void whenTestQueueThenHasCircle() {
        arrayNode[0].next = arrayNode[1];
        arrayNode[1].next = arrayNode[2];
        arrayNode[2].next = arrayNode[3];
        arrayNode[3].next = arrayNode[4];
        arrayNode[4].next = arrayNode[0];
        assertThat(testList.hasCycle(arrayNode[0]), is(true));
    }

    @Test
    public void whenTestQueueThenHasCircleInMiddle() {
        arrayNode[0].next = arrayNode[1];
        arrayNode[1].next = arrayNode[2];
        arrayNode[2].next = arrayNode[3];
        arrayNode[3].next = arrayNode[2];
        arrayNode[4].next = arrayNode[1];
        assertThat(testList.hasCycle(arrayNode[0]), is(true));
    }

    @Test
    public void whenTestQueueThenHasCircleAtTheBeginning() {
        arrayNode[0].next = arrayNode[1];
        arrayNode[1].next = arrayNode[4];
        arrayNode[2].next = arrayNode[3];
        arrayNode[3].next = arrayNode[2];
        arrayNode[4].next = arrayNode[1];
        assertThat(testList.hasCycle(arrayNode[0]), is(true));
    }

    @Test
    public void whenTestQueueThenHasNotCircle() {
        arrayNode[0].next = arrayNode[1];
        arrayNode[1].next = arrayNode[2];
        arrayNode[2].next = arrayNode[4];
        arrayNode[3].next = arrayNode[2];
        assertThat(testList.hasCycle(arrayNode[0]), is(false));
    }
}