package ru.job4j.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class Test PhoneDictionary.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 25.02.2018
 */
public class PhoneDictionaryTest {
        /**
         * Field phones.
         */
        private final PhoneDictionary phones = new PhoneDictionary();

        @Before
        public void loadOut() {
            this.phones.add(new Person("Kseniya", "Shustova", "0802435", "Saint Petersburg"));
            System.out.println("execute before method");
        }
        @After
        public void backOut() {
            System.out.println("execute after method");
        }

    /**
     * Test Find by name.
     */
    @Test
    public void whenFindByName() {
        List<Person> persons = phones.find("Kseniya");
        assertThat(persons.iterator().next().getSurname(), is("Shustova"));
    }

    /**
     * Test remove person.
     */
    @Test
    public void whenRemovePerson() {
        Person person = new Person("Kseniya", "Shustova", "0802435", "Saint Petersburg");
        phones.add(person);
        assertThat((phones.remove(person)), is(true));
    }
    /**
     * Test update person.
     */
    @Test
    public void whenUpdatePerson() {
        Person replaceable = new Person("Kseniya", "Shustova", "0802435", "Saint Petersburg");
        this.phones.add(replaceable);
        Person person = new Person("Ivan", "Ivanov", "1234567", "Spb");
        assertThat(phones.update(replaceable, person), is(replaceable));
    }
}
