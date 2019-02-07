package ru.job4j.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PackZip {

    public void zip(Args args) throws IOException {
        String zip_file = args.output() + "/" + "archive.zip";
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip_file));
        File fileSource = new File(args.directory());
        this.addDirectory(zout, fileSource);
    }

    private void addDirectory(ZipOutputStream zout, File fileSource) throws IOException {
        File[] files = fileSource.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addDirectory(zout, files[i]);
                continue;
            }

            FileInputStream fis = new FileInputStream(files[i]);
            zout.putNextEntry(new ZipEntry(files[i].getPath()));
            byte[] buffer = new byte[2048];
            while ((fis.read(buffer)) != -1) {
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        }
    }
}
