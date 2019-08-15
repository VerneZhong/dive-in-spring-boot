package com.zxb.diveinspringboot.autoconfigure.service;

/**
 * @author Mr.zxb
 * @date 2019-08-15 09:05
 */
public interface CalculateService {

    /**
     * 从多个整数sum求和
     * @param values
     * @return
     */
    Integer sum(Integer... values);
}
