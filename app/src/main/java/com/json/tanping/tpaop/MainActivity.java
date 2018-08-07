package com.json.tanping.tpaop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.json.tanping.tpaoplib.AopProxyFactory;
import com.json.tanping.tpaoplib.anniotions.EnableScanAspect;
import com.json.tanping.tpaoplib.anniotions.Pointcut;

/**
 * @author tanping
 */
public class MainActivity extends AppCompatActivity implements IAop {
    IAop aop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        aop =  AopProxyFactory.createProxy(this,new DemoAspect(),IAop.class);
        aop.say("hello");
//        Log.d("hello","end ");

        TextView tv = findViewById(R.id.tv_demo);
//        tv.setText("xxx");
    }


    @Pointcut(before = "aop",after = "aop")
    @Override
    public void say(String str) {
        Log.d("hello","say hello  :" +str + " : " +Thread.currentThread().getName());
        TextView tv = findViewById(R.id.tv_demo);
        tv.setText("xxxddd");
    }



}
