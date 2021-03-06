package com.zmh.nettyserver.timehandler;

import com.zmh.nettyserver.pojoSolution.TimeClientHandler2;
import com.zmh.nettyserver.pojoSolution.TimeDecoder1;
import com.zmh.nettyserver.pojoSolution.TimeEncoder2;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        String         host        = "localhost";
        int            port        = Integer.parseInt("8080");
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeClientHandler());
//                    ch.pipeline().addLast(new TimeClientHandler1());  //first solution
//                    ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                    ch.pipeline().addLast(new TimeDecoder1(),new TimeClientHandler2());
                }
            });

            //start the client
            ChannelFuture f = b.connect(host, port).sync();

            //wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
