package ru.job4j.sort;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Class DepartmentSortTest.
 * @author  shustovakv
 * @since 20.04.2018
 */
public class DepartmentSortTest {

    /**
     * Test ascent order for one string.
     */
    @Test
    public void whenDepartmentsAreSortedInAscendingOrderThenSorted() {
        String[] departmentCodes = {"K1/SK1/SSK1"};
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {"K1", "K1/SK1", "K1/SK1/SSK1"};
        assertThat(ds.departmentSortAscend(), is(result));
    }

    /**
     * Test ascent order for seven strings.
     */
    @Test
    public void whenSevenOfDepartmentsAreSortedInAscendingOrderThenSorted() {
        String[] departmentCodes = {
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        };
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2",
        };
        assertThat(ds.departmentSortAscend(), is(result));
    }
    @Test
    public void whenSevenOfDepartmentsAreSortedInAscendingOrderThenSortedAscending() {
        String[] departmentCodes = {
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
        };
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
        };
        assertThat(ds.departmentSortAscend(), is(result));
    }

    @Test
    public void whenSevenOfDepartmentsAreSortedInAscendingOrderThenSortedDescending() {
        String[] departmentCodes = {
                "K2",
                "K1/SK2",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
                "K1/SK1",
                "K1",
        };
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
        };
        assertThat(ds.departmentSortAscend(), is(result));
    }
    @Test
    public void whenSevenOfDepartmentsAreSortedInMixOrderThenSortedDescending() {
        String[] departmentCodes = {
                "K1",
                "K2",
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
        };
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
        };
        assertThat(ds.departmentSortAscend(), is(result));
    }
    /**
     * Test ascent order for one string.
     */
    @Test
    public void whenDepartmentsAreSortedInDescendingOrderThenSorted() {
        String[] departmentCodes = {"K2/SK1/SSK2"};
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {"K2", "K2/SK1", "K2/SK1/SSK2"};
        assertThat(ds.departmentSortDescend(), is(result));
    }

    /**
     * Test descent order.
     */
    @Test
    public void whenDepartmentsAreSortedInDescendingOrder() {
        String[] departmentCodes = {
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        };
        DepartmentSort ds = new DepartmentSort(departmentCodes);
        String[] result = {
                "K2",
                "K2/SK1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1",
                "K1",
                "K1/SK2",
                "K1/SK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1"
        };
        assertThat(ds.departmentSortDescend(), is(result));
    }
}
