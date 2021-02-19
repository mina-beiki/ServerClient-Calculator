package server;


public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(7660);
        server.runServer();
    }
}
