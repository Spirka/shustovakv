package ru.job4j.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class Client.
 *
 * @author shustovakv
 * @since 08.03.2019
 */
public class Client {
    private final Socket socket;
    private static final int PORT = 1111;
    private static final String IP = "127.0.0.1";

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String request;
        String response;
        do {
            request = console.nextLine();
            out.println(request);
            if (!"exit".equals(request)) {
                response = in.readLine();
                while (!response.isEmpty()) {
                    System.out.println(response);
                    response = in.readLine();
                }
            }
        } while (!("exit".equals(request)));
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getByName(IP), PORT)) {
            new Client(socket);
        }
    }
}
