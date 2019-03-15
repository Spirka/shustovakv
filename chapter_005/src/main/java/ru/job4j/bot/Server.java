package ru.job4j.bot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class Server.
 *
 * @author shustovakv
 * @since 08.03.2019
 */
public class Server {
    private final Socket socket;
    private static final int PORT = 4004;

    Server(Socket socket) {
        this.socket = socket;
    }

    void init() {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String ask;
            do {
                System.out.println("wait command...");
                ask = in.readLine();
                System.out.println(ask);
                if ("Hello Oracle".equals(ask)) {
                    out.println("Hello, dear friend, I'm an Oracle");
                    out.println();
                } else if (!("exit".equals(ask))) {
                    out.println("I don't understand");
                    out.println();
                }
            } while (!("exit".equals(ask)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(PORT).accept()) {
            new Server(socket).init();
        }
    }
}