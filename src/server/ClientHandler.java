package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private int clientNum;
    private String operator ;
    private int result ;
    private ServerSocket serverSocket = null;
    private Socket connectionSocket = null;
    private OutputStream output ;
    private InputStream input ;

    public ClientHandler(int clientNum, Socket connectionSocket, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.connectionSocket = connectionSocket;
        this.clientNum = clientNum;
        operator = "";
        result = 0;
    }

    @Override
    public void run() {
        int num ;
        String numStr  ;
        try {
            output = connectionSocket.getOutputStream();
            input = connectionSocket.getInputStream();
            String clientMessage = "", serverMessage = "";
            byte[] buffer = new byte[4096];
            //read client message :
            while (true) {

                int count;
                //reading one line of input :
                count = input.read(buffer);
                if(count==-1){
                    //when client sent "over" .
                    System.out.println("Client"+clientNum+" is closed . ");
                    break;
                }
                clientMessage = new String(buffer, 0, count);
                System.out.println("-From client" + clientNum + " : " + clientMessage);

                String[] strArray = clientMessage.split(" ");
                String tool = strArray[0];
                String str = strArray[1] ;
                System.out.println("tool = "+tool);
                System.out.println("str = "+str);
                //if it is a number :
                if(tool.equals("0")){
                    numStr = str ;
                    num = Integer.parseInt(numStr);

                    //the first time :
                    if(result==0){
                        System.out.println("the first time --> setting the results .");
                        result = num ;
                    }else {
                        System.out.println("doing calculation .");
                        //Do Calculation :
                        switch (operator) {
                            case "+": {
                                result = result + num;
                                System.out.println(result);
                                break;
                            }
                            case "-": {
                                result = result - num;
                                System.out.println(result);
                                break;
                            }
                            case "*": {
                                result = result * num;
                                System.out.println(result);
                                break;
                            }
                            case "/": {
                                result = result / num;
                                System.out.println(result);
                                break;
                            }
                            case "=": {
                                System.out.println(result);
                                break;
                            }
                        }
                    }
                }
                //if it is an operator :
                if(tool.equals("1")){
                    System.out.println("setting operator . ");
                    operator = str ;
                }

                //send message to client : (saves the last string to repository and sends it )
                serverMessage = result+"" ;
                System.out.println("+To Client"+ clientNum + " : " + serverMessage);
                if(operator.equals("=") && tool.equals("1")){
                    result = 0 ;
                }
                // write to client :
                output.write(serverMessage.getBytes());
                output.flush();
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            input.close();
            output.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
