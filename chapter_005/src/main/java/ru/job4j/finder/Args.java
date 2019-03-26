package ru.job4j.finder;

/**
 * Class Args.
 *
 * @author shustovakv
 * @since 19.03.2019
 */
public class Args {

    private String directory;
    private String inputFilename;
    private String mask;
    private String regExp;
    private String outputFilename;

    public Args(String[] args) {
        if (args.length == 0) {
            System.out.println("Не найдено ключей!\n");
                    help();
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-d")) {
                    this.directory = args[++i];
                } else if (args[i].equals("-n")) {
                    inputFilename = args[++i];
                } else if (args[i].equals("-o")) {
                    outputFilename = args[++i];
                } else if (args[i].equals("-m")) {
                    mask = inputFilename;
                    inputFilename = null;
                } else if (args[i].equals("-r")) {
                    regExp = inputFilename;
                    inputFilename = null;
                } else if (args[i].equals("-f")) {
                    regExp = null;
                    mask = null;
                }
            }
        }
    }

    private void help() {
        System.out.println("Ключи:\n"
                + "        -d - директория в которой начинать поиск.\n"
                + "        -n - имя файл, маска, либо регулярное выражение.\n"
                + "        -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.\n"
                + "        -o - результат записать в файл."
                + "Пример использования: java -jar find.jar -d c:/ -n *.txt -m -o log.txt");
    }

    public String getDirectory() {
        return this.directory;
    }

    public String getInputFilename() {
        return this.inputFilename;
    }

    public String getMask() {
        return mask;
    }

    public String getRegExp() {
        return regExp;
    }

    public String getOutputFilename() {
        return outputFilename;
    }
}
