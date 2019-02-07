package ru.job4j.threads;

import java.io.IOException;

public class StartZip {
    public static void main(String[] args) throws IOException {
        PackZip packZip = new PackZip();
        packZip.zip(new Args(args));
    }
}
