package com.starry.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) {
//        softReferenceEnough();
        softReferenceNotEnough();
    }

    /**
     * 在内存空间足够的情况下，软引用不会被回收
     */
    private static void softReferenceEnough() {
        Object object1 = new Object();   // 强引用
        SoftReference<Object> softReference = new SoftReference<>(object1);   // 软引用

        System.out.println(object1);
        System.out.println(softReference);

        object1 = null;
        System.gc();

        System.out.println(object1);
        System.out.println(softReference);
    }

    /**
     * 内存空间不足时，软引用会被回收掉  -Xms5m -Xmx5m
     */
    private static void softReferenceNotEnough() {
        Object object1 = new Object();   // 强引用
        SoftReference<Object> softReference = new SoftReference<>(object1);   // 软引用

        System.out.println(object1);
        System.out.println(softReference.get());

        object1 = null;

        try {
            Byte[] byte1 = new Byte[30*1024*1024];
        } catch (Throwable a) {
            a.printStackTrace();
        }finally {
            System.out.println(object1);
            System.out.println(softReference.get());
        }
    }
}
