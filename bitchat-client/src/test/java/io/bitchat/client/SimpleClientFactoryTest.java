package io.bitchat.client;

import io.bitchat.core.ServerAttr;
import io.bitchat.packet.Packet;
import io.bitchat.packet.Request;
import io.bitchat.packet.factory.PacketFactory;
import io.bitchat.serialize.FastJsonSerializer;
import io.bitchat.serialize.Serializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Auth: lz
 * @Date: 2020/9/8
 */
public class SimpleClientFactoryTest {
    private Serializer serializer;

    @Before
    public void before() {
        serializer = FastJsonSerializer.getInstance();
    }

    @Test
    public void testGetInstance() throws InterruptedException {
        ServerAttr serverAttr = ServerAttr.getLocalServer(8864);

        ClientFactory clientFactory = SimpleClientFactory.getInstance();
        Client client = clientFactory.newClient(serverAttr);
        client.connect();

        Request request = new Request();
        request.setServiceName("order");
        request.setMethodName("MethodName");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "张三");

        request.setParams(hashMap);

        Packet packet = PacketFactory.newRequestPacket(request, 1L);
        packet.setHandleAsync(false);
        byte[] content = serializer.serialize(packet);
        Packet deserializePacket = serializer.deserialize(content, packet.getClass());

        // while (true) {
            client.sendRequest(deserializePacket);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TimeUnit.SECONDS.sleep(1);
        // }
    }
}