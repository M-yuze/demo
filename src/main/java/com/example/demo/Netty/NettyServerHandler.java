/*
package com.example.demo.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    */
/**
     * 当客户端连接服务器完成就会触发该方法
     * @param ctx
     * @throws Exception
     *//*

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接通道建立完成");
        //super.channelActive(ctx);
    }

    */
/**
     * 获取客户端发送的数据
     * @param ctx   上下文对象，含有通道channel，管道pipeline
     * @param msg   就是客户端发送的数据
     * @throws Exception
     *//*

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf =(ByteBuf) msg;
        System.out.println("收到客户端的消息：" + buf.toString(CharsetUtil.UTF_8));
        //super.channelRead(ctx, msg);
    }

    */
/**
     * 出现异常时如何处理
     * @param ctx
     * @param cause
     * @throws Exception
     *//*

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }
}
*/
