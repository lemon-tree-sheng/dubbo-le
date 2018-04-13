package org.sheng.dubbo.minirpc.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author shengxingyue, created on 2018/4/13
 */
public class ConsumerProxy {

    public static <T> T getService(final Class<T> serviceInterface, final String host, final int port) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[]{serviceInterface}, (proxy, method, args) -> {
            Object result;
            Socket socket = new Socket(host, port);
            try (ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream())) {
                os.writeUTF(method.getName());
                os.writeObject(args);
                try (ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {
                    result = is.readObject();
                    if (result instanceof Throwable) {
                        throw (Throwable) result;
                    }
                }
            }
            return result;
        });
    }
}
