package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        final int port = 8005;
        ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
        Socket clientSocket = serverSocket.accept(); // ждем подключения
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println("New connection accepted");

            out.println("Write your name");
            final String name = in.readLine();
            System.out.println(name + " connected to server");

            while (true) {
                out.println("Are you child? (yes/no)");
                final String option = in.readLine();
                if (option.equals("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    break;
                } else if (option.equals("no")) {
                    out.println(String.format(
                            "Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    break;
                } else {
                    out.println("Please write \"yes\" or \"no\"");
                }
            }
        }
    }
}
