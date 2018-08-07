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

import android.util.Log;

import com.json.tanping.tpaoplib.anniotions.Pointcut;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 项目名称: YOSHOP
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/8/6 16:57
 * @author tanping
 */
class AopInvocationHandler<T> implements InvocationHandler {

    private T entity;
    private Object aspect;

    public HashMap<String,AspectAround> beforeAspect;
    public HashMap<String,AspectAround> afterAspect;
    /**
     * 延迟加载
     */
    public boolean delayLoad = true;
    public   AopInvocationHandler(T t,Object aspect) {
        entity = t;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (delayLoad){
            delayLoad = false;
            AspectAround.analysis(aspect,this);
        }

        Pointcut pointcut = (Pointcut) AspectAround.aspectAround(method);
        AspectAround aspectAround;
        //before
        if (pointcut!= null && beforeAspect!=null && isNotEmpty(pointcut.before())){
            aspectAround = beforeAspect.get(pointcut.before());
            if (aspectAround !=null){
                aspectAround.invoke(aspect,args);
            }
        }

        // really
        Object obj = method.invoke(entity,args);

        //after
        if (pointcut!=null && afterAspect!=null && isNotEmpty(pointcut.after())){
            aspectAround = afterAspect.get(pointcut.after());
            if (aspectAround !=null){
                aspectAround.invoke(aspect,args);
            }
        }
        return obj;
    }


    /**
     * 分析结构
     * @param name 类型
     * @param method 方法
     */
    public void putBefore(String name,Method method){
        if (beforeAspect == null){
            beforeAspect = new HashMap<>();
        }
        putValue(beforeAspect,name,method);
    }

    /**
     * 分析结构 after
     * @param name 类型
     * @param method 方法
     */
    public void putAfter(String name,Method method){
        if (afterAspect == null){
            afterAspect = new HashMap<>();
        }
        putValue(afterAspect,name,method);
    }

    /**
     *
     * @param map
     * @param name
     * @param method
     */
    public void putValue(HashMap<String,AspectAround> map,String name,Method method){
        AspectAround aspect =  map.get(name);
        if (aspect == null){
            aspect = new AspectAround();
        }

        aspect.methods.add(method);
        map.put(name,aspect);
    }

    public static boolean isNotEmpty(String str){
        return str != null && !str.trim().equals("");
    }
}
