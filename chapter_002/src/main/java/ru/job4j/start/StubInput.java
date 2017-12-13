package ru.job4j.start;

import ru.job4j.tracker.Input;
/**
 * Class StubInput.
 * @author  shustovakv
 * @since 04.12.2017
 */
public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return answers[position++];
    }

    @Override
    public int ask(String question, int[] range) {
        return Integer.valueOf(answers[position++]);
    }
}
