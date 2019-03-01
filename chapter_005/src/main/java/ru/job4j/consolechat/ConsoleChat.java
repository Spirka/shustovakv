package ru.job4j.consolechat;

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
    private static final String STOP = "stop";
    private static final String CONTINUE = "continue";
    private static final String EXIT = "exit";
    private static final List<String> COMMAND_LIST = Arrays.asList(STOP, CONTINUE, EXIT);
    private List<String> answers;
    private File logFile;

    private ConsoleChat() {
        InputStream phrasesDirectory = getClass().getResourceAsStream("phrases.txt");
        this.answers = phrasesList(phrasesDirectory);
        this.logFile = createLogFile();
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
    public void chat(List<String> answers) {
        System.out.println("Command list: " + COMMAND_LIST);
        String userMessage;
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
                recordLog(logFile, textLog.toString());
            } while (!userMessage.equals(EXIT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds lines of a text file to the collection.
     * @param is Path to the answer file.
     * @return Collection of answers.
     */
    private List<String> phrasesList(InputStream is) {
        this.answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                answers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }
    /**
     * Record the history of the conversation in the log file.
     * @param logFile Log-file.
     * @param log A string to write to the log.
     */
    private void recordLog(File logFile, String log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            bw.write(log);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The file is created in the temporary folder.
     * @return Log-file
     * @throws IOException
     */
    private File createLogFile()  {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + "/consoleChatLog";
        new File(parent).mkdirs();
        new File(parent + "/chatLog").mkdirs();
        File file = new File(parent + "/chatLog/log.txt");
        try {
            new File(parent + "//chatLog/log.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        List<String> answers = consoleChat.answers;
        consoleChat.chat(answers);
    }
}
