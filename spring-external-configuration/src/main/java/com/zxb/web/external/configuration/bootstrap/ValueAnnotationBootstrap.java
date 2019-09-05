package com.zxb.web.external.configuration.bootstrap;

import com.zxb.web.external.configuration.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * @Value 注解示例
 * @author Mr.zxb
 * @date 2019-09-05 10:44
 */
@EnableAutoConfiguration
public class ValueAnnotationBootstrap implements BeanFactoryAware, EnvironmentAware {

    /**
     * 方式三：Autowired 注入 Environment
     */
    @Autowired
    @Qualifier(ENVIRONMENT_BEAN_NAME)
    private Environment environment;

    /**
     * 方式四：BeanFactory 依赖查找的方式注入 Environment
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 在上下文里有且仅有一个 environment Bean
        if (this.environment != beanFactory.getBean(ENVIRONMENT_BEAN_NAME , Environment.class)) {
            throw new IllegalStateException();
        }
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 方式二：Environment 构造器注入
     */
//    @Autowired
//    public ValueAnnotationBootstrap(Environment environment) {
//        this.environment = environment;
//    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(ValueAnnotationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        User user = context.getBean("user2", User.class);

        // 系统属性的优先级高于，application.properties的配置属性
        System.out.println("Application user name: " + user);

        context.close();

    }

//    @Bean
    public User user(@Value("${user.id}") Long id,
                     @Value("${user.name}") String name) {
        return new User(id, name);
    }

    @Bean
    public User user(@Value("${user.id}") Long id,
                     @Value("${user.name}") String name,
                     @Value("${user.age:${my.user.age:32}}") Integer age) {
        final User user = new User(id, name);
        user.setAge(age);
        return user;
    }

    /**
     * 方式一：Environment 方法注入
     * @return
     */
    @Bean
//    @Autowired
    public User user2(
//            Environment environment
    ) {
        final Long id = environment.getRequiredProperty("user.id", Long.class);
        final String name = environment.getRequiredProperty("user.name", String.class);
        final Integer age = environment.getProperty("user.age", Integer.class,
                environment.getProperty("my.user.age", Integer.class, 20));
        final User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

}
