package org.sheng.dubbo.sample.provider;

import lombok.extern.slf4j.Slf4j;
import org.sheng.dubbo.sample.api.DemoService;
import org.sheng.dubbo.sample.api.dto.DemoServiceDto;
import org.sheng.dubbo.sample.api.dto.DemoServiceDto2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shengxingyue, created on 2018/4/14
 */
@Slf4j
public class DemoServiceImpl implements DemoService {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Override
    public List<DemoServiceDto> queryDemoServiceDtoList() {
        DemoServiceDto demoServiceDto = new DemoServiceDto();
        List<DemoServiceDto> result = new ArrayList<>();
        result.add(demoServiceDto);
        log.info("第 {} 次提供服务", count.incrementAndGet());
        return result;
    }

    @Override
    public void sendBigData(DemoServiceDto2 demoServiceDto2) {
        log.info("服务端收到数据 {}", demoServiceDto2);
    }
}
