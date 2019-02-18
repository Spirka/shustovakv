package ru.job4j.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Class ConsoleChat.
 *
 * @author shustovakv
 * @since 19.02.2019
 */
public class ConsoleChat {
    private static final String STOP = "stop";
    private static final String CONTINUE = "continue";
    private static final String EXIT = "exit";
    private static final List<String> COMMAND_LIST = Arrays.asList(STOP, CONTINUE, EXIT);
    private List<String> answers;
    private String pathLog;

    public ConsoleChat(String[] args) {
        this.pathLog = args[1];
    }

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
    public void chat(List<String> answers, File logFile) {
        System.out.println("Command list: " + COMMAND_LIST);
        String userMessage = null;
        boolean stop = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                StringBuilder textLog = new StringBuilder();
                userMessage = br.readLine();
                textLog.append(userMessage);
                if (!userMessage.equals(EXIT)) {
                    if (userMessage.equals(STOP)) {
                        stop = true;
                    } else if (stop && userMessage.equals(CONTINUE)) {
                        stop = false;
                    } else if (!stop) {
                        String answer = responseComp(answers);
                        System.out.println(answer);
                        textLog.append(System.lineSeparator()).append(answer);
                    }
                }
            } while (!userMessage.equals(EXIT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
