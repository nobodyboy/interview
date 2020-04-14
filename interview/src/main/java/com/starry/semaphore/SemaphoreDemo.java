package com.starry.semaphore;

import java.util.concurrent.Semaphore;

// 信号量  多个线程抢占多个资源
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 6; i++) { // 模拟6辆车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 车辆进来");

                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "\t 车辆 等待3秒后离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
