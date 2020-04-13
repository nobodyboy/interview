package com.starry.reentrantLock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t myLock invoked");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    // 减锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t myUnLock invoked");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo demo = new SpinLockDemo();

        new Thread(()->{
            demo.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            demo.myUnLock();
        }, "AA").start();

        Thread.sleep(1000);

        new Thread(()->{
            demo.myLock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            demo.myUnLock();
        }, "BB").start();
    }
}
