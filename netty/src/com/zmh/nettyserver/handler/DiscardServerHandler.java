package com.zmh.nettyserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //discard the received data silently
        /*
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.print((char)in.readByte());
                System.out.flush();
            }
        }finally {
            ReferenceCountUtil.release(in);
        }
        */
        ctx.writeAndFlush(msg);//echo telnet
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //close the connection when an exception is raised
        cause.printStackTrace();
        ctx.close();
    }
}
