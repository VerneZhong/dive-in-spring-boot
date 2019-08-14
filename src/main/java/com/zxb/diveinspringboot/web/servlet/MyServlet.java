//package com.zxb.diveinspringboot.web.servlet;
//
//import javax.servlet.AsyncContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 传统Servlet组件，基于Servlet注解实现
// * @author Mr.zxb
// * @date 2019-08-13 15:23
// */
////路径映射
//@WebServlet(urlPatterns = "/my/servlet",
//        // 开启异步
//        asyncSupported = true)
//public class MyServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        // 异步上下文
//        AsyncContext asyncContext = req.startAsync();
//
//        asyncContext.start(() -> {
//            try {
//                resp.getWriter().println("Hello world");
//
//                // 触发完成，显式操作
//                asyncContext.complete();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}
