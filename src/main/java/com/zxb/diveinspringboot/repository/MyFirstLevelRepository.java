package com.zxb.diveinspringboot.repository;

import com.zxb.diveinspringboot.annotation.FirstLevelRepository;
import com.zxb.diveinspringboot.annotation.SecondLevelRepository;
import org.springframework.stereotype.Component;

/**
 * 派生性
 * @author Mr.zxb
 * @date 2019-08-14 14:07
 */
@SecondLevelRepository(value = "myFirstLevelRepository") // Bean名称
//    @Component(value = "myFirstLevelRepository")
public class MyFirstLevelRepository {
}
