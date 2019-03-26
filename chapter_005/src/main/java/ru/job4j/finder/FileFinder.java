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

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.nio.file.*;
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

    /**
     * List of paths
     */
    private List<Path> fileList;

    private Args args;

    public FileFinder(Args args) {
        this.args = args;
    }

    private void sort() throws IOException {
        if (this.args.getInputFilename() != null) {
            this.fileList = findByName(this.args.getInputFilename());
        } else if (args.getMask() != null) {
            this.fileList = findByMask(this.args.getMask());
        } else if (args.getRegExp() != null) {
            this.fileList = findByRegEx(this.args.getRegExp());
        }
        writeToFile(this.args.getOutputFilename());
    }

    public static void main(String[] args) throws IOException {
        FileFinder fileFinder = new FileFinder(new Args(args));
        fileFinder.sort();
    }

    private List<Path> findByName(String name) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.getDirectory()))) {
            return filesStream
                    .map(Path::toFile)
                    .filter(file -> file.getName().equals(name))
                    .map(File :: toPath)
                    .collect(Collectors.toList());
        }
    }

    private List<Path> findByMask(String mask) throws IOException {
        FileFilter fileFilter = new WildcardFileFilter(mask);
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.getDirectory()))) {
            return filesStream
                    .map(Path::toFile)
                    .filter(fileFilter::accept)
                    .map(File::toPath)
                    .collect(Collectors.toList());
        }
    }

    private List<Path> findByRegEx(String regEx) throws IOException {
        FileFilter fileFilter = new RegexFileFilter(regEx);
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.getDirectory()))) {
            return filesStream
                    .map(Path :: toFile)
                    .filter(fileFilter :: accept)
                    .map(File :: toPath)
                    .collect(Collectors.toList());
        }
    }

    private void writeToFile(String out) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(out)))) {
            this.fileList.forEach(pw::println);
        }
    }
}
