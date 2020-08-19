package com.zxb.web.external.configuration.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Mr.zxb
 * @date 2019-09-05 09:49
 */
public class User {

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;

    @Value("${user.age}")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "id=" +id + ", name=" + name + ", age=" + age + "}";
    }
}
