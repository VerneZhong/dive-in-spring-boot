package com.zxb.diveinspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * 基于配置方式自定义条件装配
 * @author Mr.zxb
 * @date 2019-08-15 09:09
 */
@Profile("Java7")
@Service
@Slf4j
public class Java7CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... values) {
        log.info("Java7 for 循环实现.");
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }
}
