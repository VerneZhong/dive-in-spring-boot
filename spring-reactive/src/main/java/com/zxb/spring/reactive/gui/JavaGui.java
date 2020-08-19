package com.zxb.spring.reactive.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Mr.zxb
 * @date 2019-08-29 10:58
 */
public class JavaGui {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("GUI 示例");
        jFrame.setBounds(500, 300, 400, 300);
        LayoutManager layoutManager = new BorderLayout(400, 300);
        jFrame.setLayout(layoutManager);
        // 鼠标点击监听
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("[线程：%s] 鼠标点击，坐标（X: %d, Y: %d）\n",
                        currentThreadName(), e.getX(), e.getY());
            }
        });
        // 窗口监听
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.printf("[线程：%s] 清除 JFrame... \n",
                        currentThreadName());
                // 清除JFrame
                jFrame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.printf("[线程：%s] 退出程序... \n", currentThreadName());
                // 退出程序
                System.exit(0);
            }
        });
        System.out.println("当前线程：" + currentThreadName());
        jFrame.setVisible(true);
    }

    private static String currentThreadName() {
        return Thread.currentThread().getName();
    }
}
