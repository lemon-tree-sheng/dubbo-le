package org.sheng.dubbo.sample.consumer;

import lombok.extern.slf4j.Slf4j;
import org.sheng.dubbo.sample.api.DemoService;
import org.sheng.dubbo.sample.api.dto.DemoServiceDto;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author shengxingyue, created on 2018/4/14
 */
@Slf4j
public class Main {
    public static final String configLocation = "classpath:dubbo-consumer.xml";

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        context.start();
        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        // 执行远程方法
        List<DemoServiceDto> result = demoService.queryDemoServiceDtoList();
        log.info("{}", result);
    }
}