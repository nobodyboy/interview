package com.starry.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 人到齐以后才可以开会，有天然屏障。
// CountDownLatch是减法，CyclicBarrier是加法.
public class CyclicBarrierDemo {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{System.out.println("七颗龙珠被找到，召唤神龙!");});

    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 龙珠被找到");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
