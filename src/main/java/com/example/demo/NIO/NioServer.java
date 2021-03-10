package com.example.demo.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//服务端，采用NIO非阻塞的模式，适用于高并发，但是还是会存在一个问题，假如连接数太多，而只有少数的连接在保持着客户端与服务端的通信，
//也就是发送消息，这就会产生大量的无用连接，如果每次都要遍历这些无用连接，就会无故消耗系统资源
public class NioServer {

    //保存客户端连接
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //创建NIO ServerSocketChannel,与BIO的serverSocket类似
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(2345));
        //设置ServerSocketChannel为非阻塞
        serverSocket.configureBlocking(false);
        System.out.println("服务启动成功！");

        while (true){
            //非阻塞模式下，调用accept()方法时不会阻塞，否则会发生阻塞
            SocketChannel socketChannel = serverSocket.accept();
            //如果有客户进行连接
            if (socketChannel != null){
                System.out.println("连接成功！");
                //设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                //保存客户端连接在List中
                channelList.add(socketChannel);
            }

            //遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()){
                SocketChannel sc = iterator.next();
                ByteBuffer allocate = ByteBuffer.allocate(128);
                //非阻塞模式read方法不会阻塞，否则会阻塞
                int read = sc.read(allocate);
                //如果有数据，把数据打印出来
                if (read > 0){
                    System.out.println("接收到消息：" + new String(allocate.array()));
                }else if (read == -1){  //如果客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }
}
