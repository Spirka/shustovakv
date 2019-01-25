package ru.job4j.threads;

import java.io.*;

/**
 * Class InputStreamFilter.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.01.2019
 */
public class InputStreamFilter {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String censor;
            while ((censor = br.readLine()) != null) {
                for (String word : abuses) {
                    if (censor.contains(word)) {
                        censor = censor.replaceAll(word, "");
                    }
                }
                out.write(censor.getBytes());
                System.out.println(out.toString());
            }
        }
    }
}
