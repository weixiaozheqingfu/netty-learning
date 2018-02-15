package com.crossoverjie.netty.learning.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;

/**
 * Function:消息处理器
 *
 * @author crossoverJie
 *         Date: 14/02/2018 00:39
 * @since JDK 1.8
 */
@ChannelHandler.Sharable
public class EchoServiceHandle  extends ChannelInboundHandlerAdapter{
    private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EchoServiceHandle.class);

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        LOGGER.info("msg={}", byteBuf.toString(CharsetUtil.UTF_8));
        //将收到的消息返回给客户端
        channelHandlerContext.write(byteBuf) ;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

        throwable.printStackTrace();
        channelHandlerContext.close();
    }
}