package ru.job4j.statistics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class Analysis.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 04.12.2018
 */
public class Analysis {

    public Info diff(List<User> previous, List<User> current) {

        int added = 0;
        int changed = 0;

        Map<Integer, String> map = new HashMap<>();

        Iterator<User> itPrev = previous.iterator();
        while (itPrev.hasNext()) {
            User temp = itPrev.next();
            map.put(temp.id, temp.name);
        }

        Iterator<User> itCur = current.iterator();
        while (itCur.hasNext()) {
            User temp = itCur.next();
            int tempID = temp.id;
            if (map.containsKey(tempID)) {
                if (!map.get(tempID).equals(temp.name)) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        int deleted = previous.size() + added - current.size();
        return new Info(added, changed, deleted);
    }


    public static class Info {

        public int added;
        public int changed;

        public int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {

            return Objects.hash(added, changed, deleted);
        }
    }

    public static class User {

        private final int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, name);
        }
    }
}
