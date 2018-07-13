package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    RoleStore roleStore = new RoleStore();

    @Before
    public void setUp() {

        this.roleStore.add(new Role("student"));
        this.roleStore.add(new Role("teacher"));
        this.roleStore.add(new Role("user"));

    }

    @Test
    public void whenAddNewRole() {
        this.roleStore.add(new Role("programmer"));
        assertThat(this.roleStore.findById("programmer").getId(), is("programmer"));
    }

    @Test
    public void whenReplaceRole() {
        this.roleStore.replace("student", new Role("doctor"));
        assertThat(this.roleStore.findById("doctor").getId(), is("doctor"));
    }

    @Test
    public void whenDeleteRole() {
        this.roleStore.delete("teacher");
        RoleStore expect = new RoleStore();
        expect.add(new Role("student"));
        expect.add(new Role("user"));
        assertThat(this.roleStore.store.size(), is(expect.store.size()));
    }

    @Test
    public void findById() {
        assertThat(this.roleStore.findById("student").getId(), is("student"));
    }
}