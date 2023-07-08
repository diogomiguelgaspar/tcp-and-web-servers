package org.academiadecodigo.flowribellas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {


// STEP2: Bind to local port and block while waiting for client connections

        ServerSocket serverSocket = new ServerSocket(9000);
        Socket clientSocket = serverSocket.accept();

// STEP3: Setup input and output streams

            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String text = in.readLine();
                System.out.println(text);
               if (text.equals("quit")){
                    serverSocket.close();
                    clientSocket.close();
                    return;
                }
            }



        //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        //out.println();





    }
}
