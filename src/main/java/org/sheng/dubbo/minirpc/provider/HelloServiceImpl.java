package org.sheng.dubbo.minirpc.provider;

import org.sheng.dubbo.minirpc.spi.HelloService;

/**
 * @author shengxingyue, created on 2018/4/13
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String content) {
        return String.format("hello %s", content);
    }
}
