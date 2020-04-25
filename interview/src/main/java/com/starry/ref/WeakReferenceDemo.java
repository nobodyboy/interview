package com.starry.ref;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        weakReferenceMethod();
    }

    /**
     * 弱引用只要发生GC就会被回收
     */
    private static void weakReferenceMethod() {
        Object object1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object1);
        System.out.println(object1);
        System.out.println(weakReference.get());

        object1  = null;
        System.gc();

        System.out.println(object1);
        System.out.println(weakReference.get());
    }
}
