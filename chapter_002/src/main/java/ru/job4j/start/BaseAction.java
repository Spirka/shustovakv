package ru.job4j.start;
/**
 * Abstract Class BaseAction.
 * @author  shustovakv
 * @since 14.12.2017
 */
public abstract class BaseAction implements UserAction {

    private final String name;
    private final int key;

    protected BaseAction(final String name, final int key) {
        this.name = name;
        this.key = key;
    }
    @Override
    public int key() {
        return this.key;
    }
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }


}
