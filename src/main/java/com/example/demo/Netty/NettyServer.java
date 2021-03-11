/*
package com.example.demo.Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

//引用多路复用器的NIO模式，还是存在着很多问题
//1、代码多，不够简洁
//2、当有效连接数过多，需要轮询这些连接，那么这个时候当有一个新的连接请求过来的时候，会导致连接失败或者加载缓慢的问题
//所有就使用了netty，这个中间件
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组bossGroup和workerGroup，含有的子线程NioEventLoopGroup的个数默认为cpu的两倍
        //bossGroup只是处理连接请求，真正的客户端页面处理，会交给workerGroup完成
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            //创建服务器端的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程来配置参数
            bootstrap.group(bossGroup,workerGroup)//设置两个线程组
                    //使用NioServerSocketChannel作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    //初始化服务器连接队列大小，服务器处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，
                    //多个客户端同时来的时候，服务器将不能处理的客户端连接请求放在队列中等待处理
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //创建通道初始化对象，设置初始化参数，
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //对workerGroup的SocketChannel设置处理器
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("netty server start...");
            //绑定端口，同步等待成功
            ChannelFuture sync = bootstrap.bind(1234).sync();
            //等待服务监听端口关闭
            sync.channel().closeFuture().sync();
        }finally {
            //退出，释放线程资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
*/
