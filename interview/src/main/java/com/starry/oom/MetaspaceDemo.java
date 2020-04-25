package com.starry.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.swing.MenuItemCheckIconFactory;

import java.lang.reflect.Method;

/**
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m  设置元空间大小
 */
public class MetaspaceDemo {
    static class OOMTest{}
    public static void main(final String[] args) {
        int i = 0;  // 计数器
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e) {
            System.out.println("*********************** 第多少次发生异常： " + i);
            e.printStackTrace();
            throw e;
        }
    }
}
