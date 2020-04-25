package com.starry.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(object, referenceQueue);

        System.out.println(object);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());  // 并没有值

        System.out.println("============================");
        object = null;
        System.gc();
        System.out.println(object);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());  // 被GC的对象会放入队列中
    }
}
