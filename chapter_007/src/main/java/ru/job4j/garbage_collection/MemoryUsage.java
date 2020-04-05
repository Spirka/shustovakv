package ru.job4j.garbage_collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryUsage {

    private static final Logger Log = LoggerFactory.getLogger(MemoryUsage.class);

    public static class User {

        private String name;

        public User(String name) {
            this.name = name;
        }

        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
//        info();

    }

}
