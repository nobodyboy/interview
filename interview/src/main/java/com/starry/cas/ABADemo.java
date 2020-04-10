package com.starry.cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "thread1").start();

        new Thread(()-> {
            try {
                Thread.sleep(1000);  // thread2睡眠1秒，保证线程1执行完毕
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicReference.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + " 执行结果：" + result + "\t  此时值为： "+ atomicReference.get());
        }, "thread2").start();

        // 执行结果为：
        // thread2 执行结果：true	  此时值为： 2019
        // thread1已经对atomicReference值进行了修改，但是thread2并不知道。所以存在ABA问题

        try {
            Thread.sleep(3000);  // thread2睡眠1秒，保证线程1执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("======================解决ABA问题==========================");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 值为：" + atomicStampedReference.getReference() + ", 第一次版本号为：" + atomicStampedReference.getStamp());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 值为：" + atomicStampedReference.getReference() + ", 第二次版本号为：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 值为：" + atomicStampedReference.getReference() + ", 第三次版本号为：" + atomicStampedReference.getStamp());
        }, "thread3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 值为：" + atomicStampedReference.getReference() + ", 第一次版本号为：" + atomicStampedReference.getStamp());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp+1);
            System.out.println(Thread.currentThread().getName() + "\t 修改结果：" + result +"\t 值为：" + atomicStampedReference.getReference() + ", 版本号为： " + atomicStampedReference.getStamp());
        }, "thread4").start();
    }
}
