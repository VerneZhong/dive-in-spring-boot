package com.zxb.spring.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步 Servlet 实现
 *
 * @author Mr.zxb
 * @date 2019-08-27 11:21
 */
@WebServlet(
        name = "asyncServlet", // Servlet 名字
        urlPatterns = "/async-servlet",
        asyncSupported = true // 激活异步
)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 判断是否支持异步
        if (req.isAsyncSupported()) {
            // 创建 AsyncContext
            final AsyncContext asyncContext = req.startAsync();

            // 设置超时时间
            asyncContext.setTimeout(50L);

            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                    println("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    resp.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    println("执行超时");
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    println("执行错误");
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {
                    println("开始执行");
                }
            });

//            final ServletResponse response = asyncContext.getResponse();
            // 设置响应媒体类型
//            response.setContentType("text/plain;charset=UTF-8");
//            // 获取字符输出流
//            PrintWriter writer = response.getWriter();
//            writer.println("AsyncServlet service method.");
//            writer.flush();
        }

    }

    public static void println(Object o) {
        System.out.println("AsyncServlet [" + Thread.currentThread().getName() + "]: " + o);
    }
}
