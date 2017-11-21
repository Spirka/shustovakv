package ru.job4j.profession;
/**
 * Class Profession.
 * @author  shustovakv
 * @since 21.11.2017
 */
public class Profession {
    /**
     * Поле name.
     */
    public String name;
    /**
     * Поле age.
     */
    public int age;
    /**
     * Поле experience.
     */
    public int experience;
    /**
     * Конструктор.
     */
    public Profession(String name, int age, int experience) {
        this.name = name;
        this.age = age;
        this.experience = experience;
    }

    /**
     * Дефолтный конструктор.
     */
    public Profession() {
    }
    /**
     * Метод getName.
     * @return name.
     */
    public String getName() {
        return name;
    }
    /**
     * Метод getAge.
     * @return age.
     */
    public int getAge() {
        return age;
    }
    /**
     * Метод getExperience.
     * @return experience.
     */
    public int getExperience() {
        return experience;
    }
}
