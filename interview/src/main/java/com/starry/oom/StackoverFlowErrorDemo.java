package com.starry.oom;

/**
 * 方法递归调用导致栈溢出
 */
public class StackoverFlowErrorDemo {
    public static void main(String[] args) {
        stackoverFlowError();
    }

    private static void stackoverFlowError() {
        stackoverFlowError();
    }
}
