package com.starry.copyOnWriteArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {
    // 列举一个ArrayList线程不安全的例子

    /**
     * 解决方案有：①Vector; ②Collections.synchronizedList(new ArrayList<>);③CopyOnWriterArrayList
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>(); //Collections.synchronizedList(new ArrayList<>()); //new ArrayList<>();   //
        for (int i = 1; i < 40; i++) {   // 当i为4时毫无异常，但是为40是则会出现java.util.ConcurrentModificationException，原因是并发修改竞争导致的。
            new Thread(()-> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
