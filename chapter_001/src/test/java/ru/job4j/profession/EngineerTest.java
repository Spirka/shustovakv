package ru.job4j.profession;

import org.junit.Test;

/**
 * Test.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class EngineerTest {
    /**
     * Test инжинер Бил контролирует проект первый.
     */
    @Test
    public void whenTheEngineerControlTheProject() {
        Engineer bill = new Engineer();
        Project first = new Project();

        bill.control(first);
    }
    /**
     * Test инжинер Бен готовит документацию для проекта второго.
     */
    @Test
    public void whenTheEngineerPreparationOfDocumentationTheProject() {
        Engineer ben = new Engineer();
        Project second = new Project();

        ben.preparationOfDocumentation(second);
    }
}
