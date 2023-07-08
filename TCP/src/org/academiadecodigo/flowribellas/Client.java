package org.academiadecodigo.flowribellas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {




        String hostName = "127.0.0.1";
        //System.out.println(hostName);
        int portNumber = 9000;

// STEP2: Open a client socket, blocking while connecting to the server
        Socket clientSocket = new Socket(hostName, portNumber);



// STEP3: Setup input and output streams

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


        //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //in.readLine();

        while (true) {

            Scanner reader = new Scanner(System.in);
            System.out.print("Enter message: ");
            String text = reader.nextLine();
            out.println(text);
            if (text.equals("quit")){
                clientSocket.close();
                return;
            }

        }


    }
}
