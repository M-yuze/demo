package com.example.demo.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true){
            System.out.println("等待连接...");
            //阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了");
            handler(clientSocket);
        }
    }

    public static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read...");
        //接收客户端的数据，阻塞方法，没有数据可读就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕");
        if (read != -1){
            System.out.println("接受到客户端的数据：" + new String(bytes,0,read));
        }
    }
}
