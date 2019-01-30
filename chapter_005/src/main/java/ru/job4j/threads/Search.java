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
    private Stack<String> folders = new Stack<>();

    List<File> files(String parent, List<String> ext){

        OnlyExt onlyExt = new OnlyExt(ext);
        this.folders.push(parent);
        while (!folders.isEmpty()) {
            String path = folders.pop();
            File tmp = new File(path);
            if (tmp.isDirectory()) {
                for(File f : Objects.requireNonNull(tmp.listFiles(onlyExt))) {
                    this.folders.push(f.getPath());
                }
            } else {
                this.files.add(tmp);
            }
        }
        return files;
    }

    public class OnlyExt implements FilenameFilter {

        public List<String> ext;

        public OnlyExt(List<String> ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            File file = new File(dir + "." + name);
            return ext.stream().anyMatch(s -> file.isDirectory() || name.endsWith("." + s));
        }
    }
}
