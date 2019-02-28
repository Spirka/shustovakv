package ru.job4j.zip;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Args.
 * @author  shustovakv
 * @since 31/01/2019
 */
public class Args {

    private String directory;
    private String output;
    private List<String> ext = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param args
     *              -d - directory - которую мы ходим архивировать
     *              -e - exclude - исключить файлы *.xml
     *              -o - output - во что мы архивируем
     */
    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                this.directory = args[++i];
            } else if (args[i].equals("-o")) {
                this.output = args[++i];
            } else if (args[i].equals("-e")) {
                this.ext.add(args[++i]);
            }
        }
    }

    public String directory() {
        return directory;
    }

    public String output() {
        return output;
    }

    public List<String> exclude() {
        return ext;
    }
}
