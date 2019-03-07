package com.flyjp.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建执行线程方式三：实现Callable 接口. 相较于实现Runnable 接口的方式，
 *      方法可以又返回值，并且可以抛出异常
 * 二、执行Callable方式，需要FutureTask实现类的支持，用于接受运算结果
 *      FutureTask时Future的实现类
 */
public class TestCallable {

    public static void main(String[] args){
        CallableDemo cd = new CallableDemo();
        //执行Callable方式，需要FutureTask实现类的支持，用于接受运算结果
        FutureTask<Integer> result = new FutureTask<Integer>(cd);
        new Thread(result).start();
        //2.接收线程运算后的结果
        try {
            Integer sum = result.get();//FutureTask 可用于闭锁
            System.out.println(sum);
            System.out.println("------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallableDemo implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        int sum = 0 ;
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            sum += i;
        }
        return sum;
    }
}
