package ru.job4j.profession;
/**
 * Class Engineer.
 * @author  shustovakv
 * @since 21.11.2017
 */
public class Engineer extends Profession {
    /**
     * Поле индустрия.
     */
    public String industry;
    /**
     * Метод getIndustry.
     * @return industry.
     */
    public String getIndustry() {
        return industry;
    }
    /**
     * Метод контроль проекта.
     */
    public void control(Project project) {

    }
    /**
     * Метод подготовка документации.
     */
    public void  preparationOfDocumentation(Project project) {

    }
}
