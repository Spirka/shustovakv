package ru.job4j.finder;

/**
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * Ключи
 * -d - директория в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class FileFinder.
 *
 * @author shustovakv
 * @since 17.03.2019
 */
public class FileFinder {

    private List<Path> fileList;

    private void sort(Args args) throws IOException {
        if (!(args.getInputFilename().isEmpty())) {
            this.fileList = findByName(args.getInputFilename(), args.getDirectory());
        } else if (!(args.getMask().isEmpty())) {
            this.fileList = findByMask(args.getMask(), args.getDirectory());
        } else if (!(args.getRegExp().isEmpty())) {
            this.fileList = findByRegEx(args.getRegExp(), args.getDirectory());
        }
        writeToFile(args.getOutputFilename());
    }

    public static void main(String[] args) throws IOException {
        FileFinder fileFinder = new FileFinder();
        Args arguments = new Args(args);
        fileFinder.sort(arguments);
        fileFinder.writeToFile(arguments.getOutputFilename());
    }

    private List<Path> findByName(String name, String directory) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(directory))) {
            return filesStream
                    .filter(f -> f.toFile().getName().equals(name))
                    .collect(Collectors.toList());
        }
    }

    private List<Path> findByMask(String mask, String directory) {
        return null;
    }

    private List<Path> findByRegEx(String regEx, String directory) {
        return null;
    }

    private void writeToFile(String out) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(out)))) {
            this.fileList.forEach(pw :: println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
