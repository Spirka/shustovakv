package ru.job4j.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskExitThenOracleExit() throws IOException {
        this.testClient("exit", LN, "");
    }

    @Test
    public void whenAskHelloThenAnswerGreatOracle() throws IOException {
        testClient(
                Joiner.on(LN).join("Hello Oracle", "exit"),
                Joiner.on(LN).join("Hello, dear friend, I am Oracle.", "", "", ""),
                Joiner.on(LN).join("Hello, dear friend, I am Oracle.", ""));
    }

    public void testClient(String consoleInput, String serverInput, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(serverInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        System.setIn(consoleInputStream);
        System.setOut(new PrintStream(consoleOutputStream));
        Client client = new Client(socket);
        client.start();
        assertThat(consoleOutputStream.toString(), is(expected));
    }
}