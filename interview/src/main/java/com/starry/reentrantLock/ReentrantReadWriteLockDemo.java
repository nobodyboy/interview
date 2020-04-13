package com.starry.reentrantLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    static class ReadWriteLock {
        volatile Map<String, Object> map = new HashMap<>();

        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        // 读的操作 共享性
        public void get(String key) throws InterruptedException {
            rwLock.readLock().lock();   // 读锁
            try {
                System.out.println(Thread.currentThread().getName() + "\t 正在读取...");
                Thread.sleep(500);
                Object value = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t 读取结果：" + value);
            } catch (Exception e) {

            } finally {
                rwLock.readLock().unlock();
            }
        }

        // 写的操作  原子性+独占性
        public void put(String key, Object value) throws InterruptedException {
            rwLock.writeLock().lock();  // 写锁
            try {
                System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
                Thread.sleep(300);
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "\t 写入完成!");
            } catch (Exception e) {

            } finally {
                rwLock.writeLock().unlock();
            }

        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    readWriteLock.put(finalI +"", finalI +"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    readWriteLock.get(finalI +"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
