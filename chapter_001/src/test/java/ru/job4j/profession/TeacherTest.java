package ru.job4j.profession;

import org.junit.Test;

/**
 * Test.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class TeacherTest {
    /**
     * Test учитель Слава ведет курсы для Андрея.
     */
    @Test
    public void whenTheTeacherConductCoursesStudent() {
        Teacher slava = new Teacher();
        Student andrey = new Student();

        slava.conductsCourses(andrey);
    }
    /**
     * Test учитель Боб учит Майка.
     */
    @Test
    public void whenTheTeacherTeachStudent() {
        Teacher bob = new Teacher();
        Student mike = new Student();

        bob.teach(mike);
    }
}
