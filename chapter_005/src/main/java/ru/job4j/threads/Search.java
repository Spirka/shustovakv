package ru.job4j.threads;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

/**
 * Class Search.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 25.01.2019
 */
public class Search {

    private List<File> files = new ArrayList<>();
    private Stack<File> folders = new Stack<>();

    List<File> files(String parent, List<String> ext) {

        OnlyExt onlyExt = new OnlyExt(ext);
        this.folders.push(new File(parent));
        while (!folders.isEmpty()) {
            File current = folders.pop();
            if (current.isDirectory()) {
                this.folders.addAll(Arrays.asList(Objects.requireNonNull(current.listFiles())));
                continue;
            }
            if (current.isFile()) {
                if (onlyExt.accept(current, current.getName())) {
                    this.files.add(current);
                }
            }

        }
        return files;
    }
    private class OnlyExt implements FilenameFilter {

        private List<String> ext;

        private OnlyExt(List<String> ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            File file = new File(dir + "." + name);
            return ext.stream().anyMatch(s -> file.isDirectory() || name.endsWith("." + s));
        }
    }
}
