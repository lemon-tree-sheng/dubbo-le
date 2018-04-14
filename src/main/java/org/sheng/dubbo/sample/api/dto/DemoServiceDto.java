package org.sheng.dubbo.sample.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shengxingyue, created on 2018/4/14
 */
@Data
public class DemoServiceDto implements Serializable {
    private String field;
}
