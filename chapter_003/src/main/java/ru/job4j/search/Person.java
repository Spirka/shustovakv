package ru.job4j.search;
/**
 * Class Person.
 * @author  shustovakv
 * @since 25.02.2018
 */
public class Person {
    /**
     * Field name.
     */
    private String name;
    /**
     * Field surname.
     */
    private String surname;
    /**
     * Field phone.
     */
    private String phone;
    /**
     * Field address.
     */
    private String address;

    /**
     * Constructor.
     * @param name name of a person
     * @param surname surname of a person
     * @param phone phone of a person
     * @param address address of a person
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
}
