package ru.job4j.generic;

/**
 * Abstract Class AbstractStore.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 12.07.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    final SimpleArray<T> store = new SimpleArray<T>();

    @Override
    public void add(T model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        int indexById = findIndexById(id);
        if (indexById > -1) {
            store.set(indexById, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndexById(id);
        if (index > -1) {
            this.store.delete(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = findIndexById(id);
        if (index > -1) {
            result = this.store.get(index);
        }
        return result;
    }

    private int findIndexById(String id) {
        int result = -1;
        for (int i = 0; i < this.store.size() + 1; i++) {
            if (this.store.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

}
