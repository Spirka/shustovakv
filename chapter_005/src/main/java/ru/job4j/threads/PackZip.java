package ru.job4j.threads;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class PackZip.
 * @author  shustovakv
 * @since 06/02/2019
 */
public class PackZip {

    public void zip(Args args) throws IOException {
        String zip_file = args.output();
        ZipOutputStream zout = null;
        try {
            zout = new ZipOutputStream(new FileOutputStream(args.directory() + "//" + zip_file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File fileSource = new File(args.directory());
        List<String> exc = args.exclude();
        this.addDirectory(zout, fileSource, exc);
    }

    private void addDirectory(ZipOutputStream zout, File fileSource, List<String> exc) throws IOException {

        File[] files = fileSource.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addDirectory(zout, files[i],exc);
                continue;
            } if (exclude(files[i], exc)) {
                FileInputStream fis = new FileInputStream(files[i]);
                zout.putNextEntry(new ZipEntry(files[i].getPath()));

                byte[] buffer = new byte[2048];
                int length;
                while((length = fis.read(buffer)) > 0) {
                    zout.write(buffer, 0, length);
                }
                zout.closeEntry();
                fis.close();

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
