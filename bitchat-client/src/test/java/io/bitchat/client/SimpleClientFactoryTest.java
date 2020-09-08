package io.bitchat.client;

import io.bitchat.core.ServerAttr;
import io.bitchat.packet.Packet;
import io.bitchat.packet.Request;
import io.bitchat.packet.factory.PacketFactory;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auth: lz
 * @Date: 2020/9/8
 */
public class SimpleClientFactoryTest extends TestCase {

    public void testGetInstance() throws InterruptedException {
        ServerAttr serverAttr = ServerAttr.getLocalServer(8864);

        ClientFactory clientFactory = SimpleClientFactory.getInstance();
        Client client = clientFactory.newClient(serverAttr);
        client.connect();

        Request request = new Request();
        request.setServiceName("heartBeat");
        request.setMethodName("MethodName");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "张三");

        request.setParams(hashMap);

        Packet packet = PacketFactory.newRequestPacket(request, 1L);
        // while (true) {
            client.sendRequest(packet);
            TimeUnit.SECONDS.sleep(1);
        // }
    }
}