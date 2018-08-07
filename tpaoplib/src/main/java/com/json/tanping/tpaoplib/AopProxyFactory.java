package com.json.tanping.tpaoplib;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

import com.json.tanping.tpaoplib.anniotions.EnableScanAspect;
import com.json.tanping.tpaoplib.anniotions.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 项目名称: YOSHOP
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/8/6 16:54
 */
public class AopProxyFactory {


    public static  <T> T createProxy(T t,Object aspect){
        if (t.getClass().getInterfaces().length==0) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        AopInvocationHandler handler = new AopInvocationHandler(t,aspect);
        AspectAround.analysis(aspect,handler);

        return  createProxy(t,aspect,t.getClass().getInterfaces());
    }

    public static  <T> T createProxy(T t,Object aspect,Class ... cls){
        if (!cls[0].isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (!aspectEnable(t.getClass())){
            return t;
        }

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),cls,new AopInvocationHandler(t,aspect));
    }


    /**
     * 是否开启切面
     * @param cls 值
     * @return 值
     */
    private   static boolean aspectEnable(Class cls) {
        Annotation[] annotations = cls.getDeclaredAnnotations();
        if (annotations !=null || annotations.length != 0){
            for (Annotation annotation : annotations){
                if (annotation.annotationType().equals(EnableScanAspect.class)) {
                    EnableScanAspect enableScanAspect = (EnableScanAspect) annotation;
                    return enableScanAspect.value();
                }
            }
        }
        return  false;
    }

}
