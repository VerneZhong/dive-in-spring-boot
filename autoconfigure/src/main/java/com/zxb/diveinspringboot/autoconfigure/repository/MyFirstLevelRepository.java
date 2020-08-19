package com.zxb.diveinspringboot.autoconfigure.repository;


import com.zxb.diveinspringboot.autoconfigure.annotation.SecondLevelRepository;

/**
 * 派生性
 * @author Mr.zxb
 * @date 2019-08-14 14:07
 */
@SecondLevelRepository(value = "myFirstLevelRepository") // Bean名称
//    @Component(value = "myFirstLevelRepository")
public class MyFirstLevelRepository {
}
