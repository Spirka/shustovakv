package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore userStore = new UserStore();

    @Before
    public void setUp() {
        this.userStore.add(new User("Ivan"));
        this.userStore.add(new User("Kate"));
    }

    @Test
    public void whenAddNewUser() {
        this.userStore.add(new User("Kirill"));
        assertThat(userStore.findById("Kirill").getId(), is("Kirill"));
    }

    @Test
    public void whenReplaceUser() {
        this.userStore.replace("Ivan", new User("Mariya"));
        assertThat(this.userStore.findById("Mariya").getId(), is("Mariya"));
    }

    @Test
    public void whenDeleteUser() {
        this.userStore.delete("Kate");
        UserStore expect = new UserStore();
        expect.add(new User("Ivan"));
        assertThat(this.userStore.store.size() - 1, is(expect.store.size()));
    }

    @Test
    public void findById() {
        assertThat(this.userStore.findById("Kate").getId(), is("Kate"));
    }
}