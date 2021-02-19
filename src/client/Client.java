package client;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private int clientPort ;
    private Socket socket ;
    private InputStream input ;
    private OutputStream output ;

    public Client (int clientPort ) {
        this.clientPort = clientPort;
    }

    //tool -->  0 = number , 1 = operator (+ , - , * , ...) , -1 = over
    public void runClient (){
        try {
            socket = new Socket("127.0.0.1", clientPort);
            System.out.println("Connected . ");
            input = socket.getInputStream();
            output = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage ( String tool , String str ){
        String clientMessage = "" , serverMessage = "" ;
        try {
            byte[] buffer = new byte[4096];
                clientMessage = tool + " " + str ;
                output.write(clientMessage.getBytes());
                output.flush();
                int count = input.read(buffer);
                serverMessage = new String(buffer, 0, count);
                System.out.println("+From Server : " + serverMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverMessage;
    }

    public void closeConnection (){
        try {
            socket.close();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
