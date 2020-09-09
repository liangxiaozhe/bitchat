package io.bitchat.server;

import io.bitchat.lang.constants.ServiceName;
import io.bitchat.packet.Payload;
import io.bitchat.packet.factory.PayloadFactory;
import io.bitchat.packet.processor.AbstractRequestProcessor;
import io.bitchat.packet.processor.Processor;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author lz
 * @date 2020/9/9
 */
@Slf4j
@Processor(name = ServiceName.ORDER)
public class OrderProcessor extends AbstractRequestProcessor {
    @Override
    public Payload doProcess(ChannelHandlerContext ctx, Map<String, Object> params) {
        log.info("接收的参数{}", params);
        return PayloadFactory.newSuccessPayload();
    }
}
