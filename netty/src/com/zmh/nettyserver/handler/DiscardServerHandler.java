package com.zmh.nettyserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
/*        ByteBuf in = (ByteBuf) msg;
        while (in.isReadable()) {
            System.out.print((char)in.readByte());
            System.out.flush();
        }*/

        ctx.writeAndFlush(msg);//echo telnet
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //close the connection when an exception is raised
        cause.printStackTrace();
        ctx.close();
    }
}
