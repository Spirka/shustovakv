package ru.job4j.profession;

import org.junit.Test;

/**
 * Test.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class DoctorTest {
    /**
     * Test Доктор Иван лечит Сергея.
     */
    @Test
    public void whenDoctorIvanToHealSergey() {
        Doctor ivan = new Doctor();
        Pacient sergey = new Pacient();

        ivan.heal(sergey);
    }

}
