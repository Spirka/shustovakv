package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
/**
 * Class MenuTracker.
 * @author  shustovakv
 * @since 11.12.2017
 */
class MenuTracker {

    private Input input;
    private Tracker tracker;
    private BaseAction[] actions = new BaseAction[6];
    private int position = 0;

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    void fillActions() {
        this.actions[position++] = this.new AddItem(0);
        this.actions[position++] = new MenuTracker.ShowItems(1);
        this.actions[position++] = new EditItem(2);
        this.actions[position++] = this.new DeleteItem(3);
        this.actions[position++] = this.new FindItemsByName(4);
        this.actions[position++] = this.new FindItemById(5);
    }


    void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    void show() {
        for (BaseAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {

        private AddItem(int key) {
            super("AddItem", key);
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

        private ShowItems(int key) {
            super("ShowItems", key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Список заявок----------");
            Item[] result = tracker.findAll();
            if (result.length == 0) {
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

        private EditItem(int key) {
            super("EditItem", key);
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

        private DeleteItem(int key) {
            super("DeleteItem", key);
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

        private FindItemsByName(int key) {
            super("FindItemsByName", key);
        }
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Поиск заявок по имени----------");
            String key = input.ask("Введите имя заявки : ");
            Item[] result = tracker.findByName(key);
            System.out.println("----------Список заявок c getName : " + key + "----------");
            for (Item item : result) {
                System.out.println(item);
            }
        }
    }
    private class FindItemById extends BaseAction {

        private FindItemById(int key) {
            super("FindItemById", key);
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
