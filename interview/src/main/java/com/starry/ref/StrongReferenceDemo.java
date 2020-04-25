package com.starry.ref;

/**
 * 说明强引用指向的对象，就算发生GC也不会被回收。
 * 因此，强引用可能造成内存泄露。
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();  // 强引用o1置为null，可以被垃圾回收；
        Object o2 = o1;   // 强引用o2还存在，就算进行垃圾回收也不会被回收；
        o1 = null;
        System.gc();
        System.out.println(o2);
    }
}
