package org.sheng.dubbo.minirpc.provider;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shengxingyue, created on 2018/4/13
 */
public class ProviderReflect {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void provideService(final Object service, final int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                try (ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {
                    String method = is.readUTF();
                    Object[] args = (Object[]) is.readObject();
                    Object result = MethodUtils.invokeExactMethod(service, method, args);
                    try (ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream())) {
                        os.writeObject(result);
                    }
                } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
