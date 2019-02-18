package ru.job4j.threads;

import java.io.*;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class PackZip.
 * @author  shustovakv
 * @since 06/02/2019
 */
public class PackZip {

    public void zip(Args args) {
        String zipFile = args.output();
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args.directory() + "//" + zipFile))) {
            File fileSource = new File(args.directory());
            List<String> exc = args.exclude();
            this.addDirectory(zout, fileSource, exc);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDirectory(ZipOutputStream zout, File fileSource, List<String> exc) throws IOException {
        Queue<File> queue = new LinkedList<>();
        queue.offer(fileSource);
        URI base = fileSource.toURI();
        while (!queue.isEmpty()) {
            File[] files = Objects.requireNonNull(queue.poll()).listFiles();
            assert files != null;
            for (int i = 0; i < files.length; i++) {
                final File file = files[i];
                String name = base.relativize(file.toURI()).getPath();
                if (file.isDirectory()) {
                    name = name.endsWith("/") ? name : name + "/";
                    zout.putNextEntry(new ZipEntry(name));
                    ((LinkedList<File>) queue).addFirst(file);
                } else if (exclude(file, exc) && !(file.getName().contains(".zip"))) {
                    FileInputStream fis = new FileInputStream(file);
                    zout.putNextEntry(new ZipEntry(name));

                    byte[] buffer = new byte[2048];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                    fis.close();
                }
            }
        }
    }

    private boolean exclude(File file, List<String> ext) {
        boolean res = true;
        if (ext != null) {
            for (String value : ext) {
                if (file.getName().endsWith(value)) {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
}
