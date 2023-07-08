package org.academiadecodigo.flowribellas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientWorker implements Runnable {
    String name;
    ServerSocket serverSocket;




    public ClientWorker(String name,ServerSocket serverSocket) {
        this.name = name;
        this.serverSocket = serverSocket;

    }
    public void listen() throws IOException {

        Socket clientSocket = serverSocket.accept();
        while (true) {

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String text = in.readLine();
            System.out.println(name + " is writing: " + text);

            if (text.equals("quit")) {
                serverSocket.close();
                clientSocket.close();
                return;
            }
        }
    }


    public void write(CopyOnWriteArrayList list) throws IOException {

        String hostName = "127.0.0.1";
        int portNumber = 8085;
        Socket clientSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        int i=list.size();
        while (i<0) {
            i--;
            out.println(list.get(0));
            if (list.equals("quit")){
                clientSocket.close();
                return;
            }

        }
    }

    @Override
    public void run() {

       while (true) {
           try {
               listen();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }


       }

    }

}
