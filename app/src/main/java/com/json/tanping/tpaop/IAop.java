package com.json.tanping.tpaop;

import android.view.View;

import com.json.tanping.tpaoplib.anniotions.Pointcut;

public  interface IAop {

    @Pointcut(before = "aop",after = "aop")
    public  void say(String str);


}

