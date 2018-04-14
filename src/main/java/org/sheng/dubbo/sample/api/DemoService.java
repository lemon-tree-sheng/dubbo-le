package org.sheng.dubbo.sample.api;

import org.sheng.dubbo.sample.api.dto.DemoServiceDto;
import org.sheng.dubbo.sample.api.dto.DemoServiceDto2;

import java.util.List;

/**
 * @author shengxingyue, created on 2018/4/14
 */
public interface DemoService {
    /**
     * 示例服务方法
     *
     * @return
     */
    List<DemoServiceDto> queryDemoServiceDtoList();

    /**
     * 消费端发送大批量数据，验证服务端拒绝服务
     *
     * @param demoServiceDto2
     */
    void sendBigData(DemoServiceDto2 demoServiceDto2);
}
