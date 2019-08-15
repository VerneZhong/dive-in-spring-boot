package com.zxb.diveinspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * 基于配置方式自定义条件装配
 * @author Mr.zxb
 * @date 2019-08-15 09:09
 */
@Profile("Java8")
@Service
@Slf4j
public class Java8CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... values) {
        log.info("Java8 stream 实现.");
        return Stream.of(values).reduce(0, Integer::sum);
    }
}
