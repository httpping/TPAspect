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

import com.json.tanping.tpaoplib.anniotions.After;
import com.json.tanping.tpaoplib.anniotions.Before;
import com.json.tanping.tpaoplib.anniotions.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 项目名称: AOP LIB
 * 类描述： 切面围绕
 * 创建人：Created by tanping
 * 创建时间:2018/8/7 8:35
 */
class AspectAround{

    public String name ;
    public List<Method> methods;


    public AspectAround() {
        methods = new LinkedList<>();
    }
    /**
     * 执行结果，将会执行
     * @param entity 值
     * @param params 值
     */
    public <T> void invoke(T entity, Object ...params)   {
        if (methods == null){
            return;
        }
        try {
            for (Method method : methods){
                if (method.getParameterTypes().length ==0){
                    method.invoke(entity);
                }else {
                    method.invoke(entity,params);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 类型 切面执行方法
     * @param aspect 切面
     * @param handler handler
     */
    public static <T> void analysis(Object aspect, AopInvocationHandler handler){
        //分析 aspect
        Method[] methods = aspect.getClass().getDeclaredMethods();
        if (methods!=null){
            for (Method method : methods){
                Annotation[] annotations = method.getDeclaredAnnotations();
                if (annotations !=null || annotations.length != 0){
                    for (Annotation annotation : annotations){
                        if (annotation.annotationType().equals(Before.class)) {
                            Before before = (Before) annotation;
                            String name = before.value();
                            handler.putBefore(name,method);
                        }else if(annotation.annotationType().equals(After.class)){
                            After before = (After) annotation;
                            String name = before.value();
                            handler.putAfter(name,method);
                        }
                    }
                }
            }
        }
    }


    /**
     * 查找 切点
     * @param method 值
     * @return 值
     */
    public  static Annotation aspectAround(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        if (annotations !=null || annotations.length != 0){
            for (Annotation annotation : annotations){
                if (annotation.annotationType().equals(Pointcut.class)) {
                    return  annotation;
                }
            }
        }
        return  null;
    }
}
