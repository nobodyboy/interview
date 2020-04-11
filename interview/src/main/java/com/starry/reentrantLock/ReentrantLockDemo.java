package com.starry.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 指的是同一个线程外层函数获得锁之后，内层递归函数仍能能获取该锁的代码，
 * 在同一个线程的外层方法获取锁的时候，在进入内层方法会自动获取锁。
 * 即线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 *
 * Synchronized和reentrantLock都是可重入锁。
 */

// synchronized和ReentrantLock都是可重入锁
public class ReentrantLockDemo {
    static class SynDemo {
        synchronized void method1(){
            System.out.println(Thread.currentThread().getName() + "\t 执行method1");
            method2();
        }

        synchronized void method2(){
            System.out.println(Thread.currentThread().getName() + "\t 执行method2");
        }
    }

    static class LockDemo implements Runnable {

        @Override
        public void run() {
            method3();
        }

        Lock lock = new ReentrantLock();
        void method3() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t 执行method3");
                method4();
            } finally {
                lock.unlock();
            }
        }
        void method4(){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t 执行method4");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        SynDemo synDemo = new SynDemo();

        new Thread(()->{
            synDemo.method1();
        }, "t1").start();
        new Thread(()->{
            synDemo.method1();
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();


        new Thread(new LockDemo(), "t3").start();
        new Thread(new LockDemo(), "t4").start();
    }

}
