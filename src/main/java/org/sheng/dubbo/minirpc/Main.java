package org.sheng.dubbo.minirpc;

import org.sheng.dubbo.minirpc.consumer.ConsumerProxy;
import org.sheng.dubbo.minirpc.spi.HelloService;

/**
 * @author shengxingyue, created on 2018/4/13
 */
public class Main {
    public static void main(String[] args) {
        HelloService helloService = ConsumerProxy.getService(HelloService.class, "127.0.0.1", 8888);
        System.out.println(helloService.sayHello("jack"));
    }
}
