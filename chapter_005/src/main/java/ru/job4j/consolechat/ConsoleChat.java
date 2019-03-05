package ru.job4j.consolechat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class ConsoleChat.
 *
 * @author shustovakv
 * @since 19.02.2019
 */
public class ConsoleChat {
    private static final Logger LOG = LogManager.getLogger(ConsoleChat.class.getName());
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжать";
    private static final String EXIT = "закончить";
    private static final String TEXT_NAME = "phrases.txt";
    private static final List<String> COMMAND_LIST = Arrays.asList(STOP, CONTINUE, EXIT);
    private List<String> answers;

    /**
     * Method Computer response
     * Random phrase from a text file is displayed in response
     * @param list text file
     * @return random phrase
     */
    private String responseComp(List<String> list) {
        int size = list.size();
        int random = (int) (Math.random() * size);
        return size > 0 ? list.get(random) : "The answer file is empty";
    }

    /**
     * Communication
     *
     */
    private void chat() {
        System.out.println("Список команд: " + COMMAND_LIST);
        LOG.info("Список команд: " + COMMAND_LIST);
        String userMessage;
        boolean stop = false;
        phrasesList();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                userMessage = br.readLine();
                LOG.info(userMessage);
                if (!userMessage.equals(EXIT)) {
                    if (userMessage.equals(STOP)) {
                        LOG.info(STOP);
                        stop = true;
                    } else if (stop && userMessage.equals(CONTINUE)) {
                        LOG.info(CONTINUE);
                        stop = false;
                    } else if (!stop) {
                        String answer = responseComp(this.answers);
                        LOG.info(answer);
                        System.out.println(answer);
                    }
                }
            } while (!userMessage.equals(EXIT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds lines of a text file to the collection.
     */
    private void phrasesList() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        this.answers = new ArrayList<>();
        try (InputStream is = classLoader.getResourceAsStream(ConsoleChat.TEXT_NAME);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.answers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       new ConsoleChat().chat();
    }
}
