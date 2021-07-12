package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        String host = "netology.local";
        int port = 8005;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (true) {
                String response = in.readLine();
                System.out.println("Server asks: " + response);
                if (response.contains("name")) {
                    out.println("Alexander");
                    System.out.println("Sent \"Alexander\"");
                } else if (response.contains("child")) {
                    out.println("no");
                    System.out.println("Sent \"no\"");
                    break;
                }
            }
            System.out.println(in.readLine());
//            out.println("CAHEK");
//            String resp = in.readLine();
//            System.out.println(resp);
        }
    }
}
