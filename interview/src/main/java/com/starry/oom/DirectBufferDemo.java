package com.starry.oom;

import java.nio.ByteBuffer;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class DirectBufferDemo {
    public static void main(String[] args) {
        // 先打印出堆外内存的大小  默认是本地内存的四分之一
        System.out.println("***************配置的maxDirectMemory: " + sun.misc.VM.maxDirectMemory()/(double)1024/1024 + "MB");

        try {
            Thread.sleep(3000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // 堆外最大内存为5MB 目前直接分配6MB，就会抛出Direct buffer memory
        ByteBuffer.allocateDirect(6*1024*1024);
    }
}
