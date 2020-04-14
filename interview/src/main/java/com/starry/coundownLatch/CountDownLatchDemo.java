package com.starry.coundownLatch;

import java.util.concurrent.CountDownLatch;

// 最形象的例子是火箭发射。
// 需要等其他线程完成以后，主线程才能执行
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 1; i < 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 国被灭...");
                latch.countDown();
            }, CountEnum.getMessage(i).getMessage()).start();   // 改造，把数字替换成其他文字
        }
        latch.await();  // 注意不能用wait()方法
        System.out.println(Thread.currentThread().getName() + "\t 秦灭六国，一统华夏!");
    }
}
