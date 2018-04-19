package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.ArrayList;

/**
 * Class MenuTracker.
 * @author  shustovakv
 * @since 11.12.2017
 */
class MenuTracker {

    private Input input;
    private Tracker tracker;
    private ArrayList<BaseAction> actions = new ArrayList<>();

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    void fillActions() {
        this.actions.add(0, new AddItem("AddItem", 0));
        this.actions.add(1, new MenuTracker.ShowItems("ShowItems", 1));
        this.actions.add(2, new EditItem("EditItem", 2));
        this.actions.add(3, new DeleteItem("DeleteItem", 3));
        this.actions.add(4, new FindItemsByName("FindItemsByName", 4));
        this.actions.add(5, new FindItemById("FindItemById", 5));
    }


    void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    void show() {
        for (BaseAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {

        private AddItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Добавление новой заявки----------");
            String name = input.ask("Введите имя заявки : ");
            String desk = input.ask("Введите описание заявки : ");
            Long create = Long.valueOf(input.ask("Введите дату создания заявки в милисекундах : "));
            Item item = new Item(name, desk, create);
            tracker.add(item);
            System.out.println("------------" + item.getName() + " заявка с Id : " + item.getId() + " добавлена в хранилище-----------");
        }
    }

    private static class ShowItems extends BaseAction {

        private ShowItems(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Список заявок----------");
            ArrayList<Item> result = tracker.findAll();
            if (result.isEmpty()) {
                System.out.println("Заявки отсутствуют");
            } else {
                for (Item item : result) {
                    System.out.println("Заявка ID: " + item.getId() + " Имя: " + item.getName() + " Описание: " + item.getDesc());
                }
            }
            System.out.println("----------Конец списка заявок----------");
        }
    }

    class EditItem extends BaseAction {

        private EditItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Обновление заявки----------");
            String id = input.ask("Введите ID заявки : ");
            Item byId = tracker.findById(id);
            if (byId == null) {
                System.out.println("Вы ввели некорректный ID");
            } else {
                String name = input.ask("Введите новое имя заявки : ");
                String desk = input.ask("Введите новое описание заявки : ");
                Long create = Long.valueOf(input.ask("Введите новую дату создания заявки в милисекундах : "));
                Item item = new Item(name, desk, create);
                item.setId(byId.getId());
                tracker.update(item);
                System.out.println("---------- Заявка с Id : " + byId.getId() + " обновлена---------");
            }
        }
    }
    private class DeleteItem extends BaseAction {

        private DeleteItem(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Удаление заявки----------");
            String id = input.ask("Введите ID заявки : ");
            Item byId = tracker.findById(id);
            if (byId == null) {
                System.out.println("Вы ввели некорректный ID");
            } else {
                tracker.delete(byId);
                System.out.println("---------- Заявка с Id : " + byId.getId() + " удалена---------");
            }
        }
    }
    private class FindItemsByName extends BaseAction {

        private FindItemsByName(String name, int key) {
            super(name, key);
        }
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Поиск заявок по имени----------");
            String key = input.ask("Введите имя заявки : ");
            ArrayList<Item> result = tracker.findByName(key);
            System.out.println("----------Список заявок c getName : " + key + "----------");
            for (Item item : result) {
                System.out.println(item);
            }
        }
    }
    private class FindItemById extends BaseAction {

        private FindItemById(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Поиск заявки по ID----------");
            String id = input.ask("Введите ID заявки : ");
            Item resultID = tracker.findById(id);
            System.out.printf("----------Заявка c ID : %s----------%n", id);
            System.out.printf("Имя заявки : %s Описание заявки : %s Дата создания : %d%n", resultID.getName(), resultID.getDesc(), resultID.getCreated());
        }
    }
}
