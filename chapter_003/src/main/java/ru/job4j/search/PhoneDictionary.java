package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Class PhoneDictionary.
 * @author  shustovakv
 * @since 25.02.2018
 */
public class PhoneDictionary {
    /**
     * List persons.
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Adding a person to the list.
     * @param person
     */
    public void add(Person person) {
        this.persons.add(person);
    }
    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().contains(key) || person.getSurname().contains(key)
                    || person.getAddress().contains(key) || person.getPhone().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }

    /**
     * Removing a person from the list
     * @param person
     * @return True if the removing was successful
     */
    public boolean remove(Person person) {
        return this.persons.remove(person);
    }

    /**
     * Update a person from the list
     * @param replaceable person
     * @param person replacement
     * @return replacement person
     */
    public Person update(Person replaceable, Person person) {
        int indexPerson = this.persons.indexOf(replaceable);
        if (indexPerson < 0) {
            return null;
        } else {
            return this.persons.set(indexPerson, person);
        }
    }
}
