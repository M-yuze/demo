package com.example.demo.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//服务端，演示传统的阻塞IO（BIO）过程-->入门
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true){
            System.out.println("等待连接...");
            //阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了");
            //handler(clientSocket);              //单线程，无法解决高并发问题
            //优化，使用多线程,但是以下多线程使用的方式再高并发的时候会导致无限创建线程，最终导致内存爆满，可以采用线程池创建固定线程数，
            //但是这样的话线程数就固定了，线程数就等于并发数，所以还是达不到高并发的需求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
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
