package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
/**
 * Class MenuTracker.
 * @author  shustovakv
 * @since 11.12.2017
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemsByName();
        this.actions[5] = new FindItemById();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавление новой заявки.");
        }
    }

    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Список всех заявок. ");
        }
    }

    class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Обновление заявки.");
        }
    }
    private class DeleteItem implements UserAction {
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки.");
        }
    }
    private class FindItemsByName implements UserAction {
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по имени.");
        }
    }
    private class FindItemById implements UserAction {
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по Id.");
        }
    }
}
