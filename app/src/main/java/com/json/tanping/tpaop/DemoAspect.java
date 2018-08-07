package com.json.tanping.tpaop;
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

import com.json.tanping.tpaoplib.anniotions.After;
import com.json.tanping.tpaoplib.anniotions.Before;

/**
 * 项目名称: YOSHOP
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/8/7 8:29
 */
public class DemoAspect {

    @Before("aop")
    public void before(String str){
        //todo
        Log.d("Aspectaop","start -- before");
    }
    @Before("aop")
    public void before2(String str){
        //todo
        Log.d("Aspectaop","start -- before2");
    }


    @After("aop")
    public void ga(String str){
        Log.d("Aspectaop","after -- after");
    }
    @After("aop")
    public void fb(String str){
        Log.d("Aspectaop","after -- after2");
    }
//    @After("aop")
    public void gw(String str){
        Log.d("Aspectaop","after -- after3");
    }
//    @After("aop")
    public void ss(String str){
        Log.d("Aspectaop","after -- after3");
    }
}
