package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int serverPort;
    private ServerSocket serverSocket = null;
    private Socket connectionSocket = null;

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        //ctr is used to give us the client number
        int ctr = 0;
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Waiting for clients ... ");
            //accepting new clients :

            ctr++;
            connectionSocket = serverSocket.accept();
            System.out.println("client" + ctr + " is accepted . ");
            Thread clientThread = new Thread(new ClientHandler(ctr, connectionSocket, serverSocket));
            clientThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //closing pool :
            pool.shutdown();
        }
    }


}
