package com.zxb.web.reactive.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Mr.zxb
 * @date 2019-09-03 09:13
 */
@SpringBootApplication
@RestController
public class WebFluxApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebFluxApplication.class, args);
    }

    /**
     * 映射路由接口- RouterFunction
     * 函数式接口映射
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        // 方式一
//        return RouterFunctions.route(request -> {
//            // 判断请求是否匹配
//            URI uri = request.uri();
//            return "/route".equals(uri.getPath()) && request.method().matches("GET");
//        }, request -> {
//            // 绑定实现处理
//            return ServerResponse.status(HttpStatus.OK)
//                    .body(Mono.just("hello webflux."), String.class);
//        });

        // 方式二
        return route(GET("/route"), this::routeMethod);
    }

    public Mono<ServerResponse> routeMethod(ServerRequest serverRequest) {
        println("route");
        return ServerResponse.status(HttpStatus.OK)
                    .body(Mono.just("hello webflux."), String.class);
    }

    @GetMapping("/mvc")
    public String mvc() {
        println("mvc");
        return "MVC";
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        println("mono");
        return Mono.just("MONO");
    }

    private void println(String message) {
        System.out.println("[ " + Thread.currentThread().getName() +" ]: " + message);
    }
}
