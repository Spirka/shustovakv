package ru.job4j.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class CheckByteStream.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 21.01.2019
 */
public class CheckByteStream {

    public boolean isNumber(InputStream in) {

        boolean checkNumber = false;
        int num;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            num = Integer.valueOf(reader.readLine());
            if (num % 2 == 0) {
                checkNumber = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checkNumber;
    }
}
