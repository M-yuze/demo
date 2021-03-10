package com.example.demo.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

//对NIO进行改进，引入了多路复用器 Selector
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {

        //创建NIO ServerSocketChannel,与BIO的serverSocket类似
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(2345));
        //设置ServerSocketChannel为非阻塞
        serverSocket.configureBlocking(false);
        //打开Selector多路复用器，处理Channel，即创建epoll，就是把所有客户端的连接都收集起来
        Selector selector = Selector.open();
        //把ServerSocketChannel注册到selector上，并且selector对客户端accept连接感兴趣
        SelectionKey register = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功！");

        while (true) {
            //阻塞等待需要处理的事件发生，比如上面的accept连接
            selector.select();
            //获取selector中注册的全部事件的 selectionKeys 实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            ////遍历selectionKeys对事件进行处理
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    //这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                    SelectionKey register1 = serverSocket.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (key.isReadable()) { //如果是OP_READ事件，则进行读取和打印
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(128);
                    int read = socketChannel.read(allocate);
                    //如果有数据，把数据打印出来
                    if (read > 0) {
                        System.out.println("接收到消息：" + new String(allocate.array()));
                    } else if (read == -1) {  //如果客户端断开，关闭socket
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
                //从事件集合中删除本次处理的key，防止下次select重复处理
                iterator.remove();
            }
        }
    }
}

