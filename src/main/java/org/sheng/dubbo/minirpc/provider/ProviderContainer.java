package org.sheng.dubbo.minirpc.provider;

import org.sheng.dubbo.minirpc.spi.HelloService;

/**
 * @author shengxingyue, created on 2018/4/13
 */
public class ProviderContainer {
    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        ProviderReflect.provideService(helloService, 8888);
    }
}
