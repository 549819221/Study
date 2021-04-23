package com.study.basics.multiThread.thread;

import com.server.basis.util.DateUtil;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * Runnable和Callable的区别
 * 相同点
 * 2、两者都可用来编写多线程程序；
 * 3、两者都需要调用Thread.start()启动线程；
 * 不同点
 * 1、两者最大的不同点是：实现Callable接口的任务线程能返回执行结果；而实现Runnable接口的任务线程不能返回结果；
 * 2、Callable接口的call()方法允许抛出异常；而Runnable接口的run()方法的异常只能在内部消化，不能继续上抛；
 */
public class ThreadIIIII  {



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Date startMainDate = new Date();
        System.out.println("main开始=============>"+ DateUtil.toString( startMainDate,DateUtil.DATE_LONG ) );

        //创建Callable的对象
        FutureTask<String> ft1 = new FutureTask<String>(new Callable<String>(){
            @Override
            public String call() throws Exception {   //重写Callable接口中的call()方法
                Date startDate = new Date();
                System.out.println("cell1开始=============>"+ DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
                Thread.sleep( 3000 );
                System.out.println("cell1结束=============>共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
                return "cell1";
            }
        });
        FutureTask<String> ft2 = new FutureTask<String>(new Callable<String>(){
            @Override
            public String call() throws Exception {   //重写Callable接口中的call()方法
                Date startDate = new Date();
                System.out.println("cell2开始=============>"+ DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
                Thread.sleep( 6000 );
                System.out.println("cell2结束=============>共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
                return "cell2";
            }
        });
        List<FutureTask> list = new ArrayList<>();
        list.add( ft1 );
        list.add( ft2 );
        List<Thread> threadList = new ArrayList<>();
        threadList.add( new Thread(ft1) );
        threadList.add( new Thread(ft2) );

        for (int i = 0; i < threadList.size(); i++) {
            Thread temp = threadList.get(i);
            temp.start();
        }
        try{
            for (int i = 0; i < list.size(); i++) {
                System.out.println( "返回结果"+list.get( i ).get() );
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main结束=============>共耗时"+(System.currentTimeMillis()  - startMainDate.getTime())+"毫秒");
    }
}
