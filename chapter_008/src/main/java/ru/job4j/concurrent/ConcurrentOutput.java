package ru.job4j.concurrent;

/**
 * Class ConcurrentOutput
 * @author Kseniya Dergunova
 * @since 05.04.2020
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
//        another.run();
//        second.run();
        System.out.println(Thread.currentThread().getName());
    }
}
