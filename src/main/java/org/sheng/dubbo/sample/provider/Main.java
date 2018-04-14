package org.sheng.dubbo.sample.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author shengxingyue, created on 2018/4/14
 */
public class Main {
    public static final String configLocation = "classpath:dubbo-provider.xml";

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        applicationContext.start();
        System.in.read();
    }
}
