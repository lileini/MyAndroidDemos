package com.example.android.aopdemo;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;

/**
 * author: Eric_Li
 * date:   On 2018/11/16
 */
@Aspect
public class CheckLoginAspectJ {
    public static final String TAG = "CheckLoginAspectJ";
    /**
     * 找到处理的切点
     * * *(..)  可以处理CheckLogin这个类所有的方法
     */
    @Pointcut("execution(@com.example.android.aopdemo.CheckLogin * *(..))")
    public void excuteCheckLogin(){

    }

    /**
     * 切面
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("excuteCheckLogin()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Annotation checkLogin = methodSignature.getMethod().getAnnotation(CheckLogin.class);
        boolean islogin= false;
        if (checkLogin!= null){
            String code = ((CheckLogin) checkLogin).code();
            Log.i(TAG,"code: "+code);
            if (islogin){
                Log.i(TAG, "checkLogin: true");
                return joinPoint.proceed();
            }else {
                Log.i(TAG, "checkLogin: false");
                return null;
            }
        }
        return joinPoint.proceed();

    }
}
