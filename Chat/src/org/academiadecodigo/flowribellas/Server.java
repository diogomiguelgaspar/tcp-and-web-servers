package org.academiadecodigo.flowribellas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {



    public Server() throws IOException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //List<Object> list = new ArrayList<Object>();
        //List<Object> synList = Collections.synchronizedList(list);
        CopyOnWriteArrayList<Object> synList = new CopyOnWriteArrayList<Object>();

        ExecutorService cachedPool1 = Executors.newCachedThreadPool();
        ExecutorService cachedPool2 = Executors.newCachedThreadPool();
        ExecutorService cachedPool3 = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(8085);

        ClientWorker clientWorker1 = new ClientWorker(getUserName(),serverSocket);
        synList.add(clientWorker1);
        ClientWorker clientWorker2 = new ClientWorker(getUserName(),serverSocket);
        synList.add(clientWorker2);
        ClientWorker clientWorker3 = new ClientWorker(getUserName(),serverSocket);
        synList.add(clientWorker3);


        cachedPool1.submit(clientWorker1);
        cachedPool2.submit(clientWorker2);
        cachedPool3.submit(clientWorker3);



        //Thread thread1 = new Thread(clientWorker1);
        //Thread thread2 = new Thread(clientWorker2);
        //thread1.start();
        //thread2.start();
        clientWorker1.write(synList);
        clientWorker2.write(synList);
        clientWorker3.write(synList);
        //broadcast(synList);





    }





    public void broadcast(CopyOnWriteArrayList list){

    }

    private static String getUserName() {

        Scanner reader = new Scanner(System.in);
        System.out.print("USERNAME ");
        return reader.nextLine();

    }

}
