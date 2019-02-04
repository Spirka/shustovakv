package ru.job4j.threads;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class Args.
 * @author  shustovakv
 * @since 31/01/2019
 */
public class Args {

    /**
     * Method init.
     *
     * @param args
     *              -d - directory - которую мы ходим архивировать
     *              -e - exclude - исключить файлы *.xml
     *              -o - output - во что мы архивируем
     */
    private void init(String[] args) {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        try {
            String buttons = br.readLine();
            if (buttons.equals("-d")) {
                System.out.println("Какую директорию будем архивировать? ");
                String sourceDir = br.readLine();
                FileOutputStream fout = new FileOutputStream(sourceDir);
                ZipOutputStream zout = new ZipOutputStream(fout);
                File fileSource = new File(sourceDir);
                this.directory(zout, fileSource);
                zout.close();
                System.out.println("Zip файл создан!");
            }
            if (buttons.equals("-e")) {
                System.out.println("Расширения файлов, которые необходимо исключить: ");
                String[] censor = br.readLine().split(" ");
                List<String> stringList = Arrays.asList(censor);
                this.exclude(stringList);
            }
            if (buttons.equals("-o")) {
                System.out.println("Во что мы архивируем? ");
                String destDir = br.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args start = new Args();
        start.init(args);
    }

    private void directory(ZipOutputStream zout, File fileSource) throws IOException {
        File[] files = fileSource.listFiles();
        System.out.println("Добавление директории <" + fileSource.getName() + ">");
        for (int i = 0; i < files.length; i++) {
            // Если file является директорией, то рекурсивно вызываем
            // метод addDirectory
            if (files[i].isDirectory()) {
                directory(zout, files[i]);
                continue;
            }

            System.out.println("Добавление файла <" + files[i].getName() + ">");
            FileInputStream fis = new FileInputStream(files[i]);
            zout.putNextEntry(new ZipEntry(files[i].getPath()));
            byte[] buffer = new byte[4048];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zout.write(buffer, 0, length);
                zout.closeEntry();
                fis.close();
            }
        }
    }

    public void exclude(List<String> exc) {

        InputStreamFilter inputStreamFilter = new InputStreamFilter();

        //inputStreamFilter.dropAbuses(exc);

    }

    public void output() {

    }
}
