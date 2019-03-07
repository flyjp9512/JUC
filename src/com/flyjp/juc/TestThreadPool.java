package com.flyjp.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外性能开销，提高了想听速度
 * 二、线程池的体系结构：
 * java.util.concurrent.Executor : 负责线程的使用与调度根接口
 *              |--ExecutorService 子接口 ：线程池的主要接口
 *                  |--ThreadPoolExecutor 线程池的实现类
 *                  |--scheduledExecutorService 子接口：负责线程的调度
 *                      |--ScheduledThreadPoolExecutor :继承了ThreadPoolExecutor，实现了scheduledExecutorService接口
 * 三、工具类：Executors
 * ExecutorService newFixedThreadPool():创建固定大小的线程池；
 * ExecutorService newCachedThreadPool():缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor():创建单个线程池。线程池中只有一个线程
 * ScheduledThreadExecutorService newScheduledThreadPool()：创建固定大小的线程，可以延迟或定时的执行任务
 */
public class TestThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);//5个线程

        List<Future<Integer>> list = new ArrayList<>();
        try{
            for(int i = 0 ; i < 10 ; i++){

                    Future<Integer> future =
                            pool.submit(new Callable<Integer>() {
                                @Override
                                public Integer call() throws Exception {
                                    int sum = 0 ;
                                    for(int i = 0 ; i < 100 ; i++){
                                        sum += i;
                                    }
                                    return sum;
                                }
                            });
                  list.add(future);
                }
            }finally {
                pool.shutdown();
            }

            for (Future<Integer> f:list
                 ) {
                System.out.println(f.get());
            }




//        ThreadPoolDemo demo = new ThreadPoolDemo();
//
//        //2.为线程池中的线程分配任务
//        for(int i = 0 ; i < 10 ; i++){
//            pool.submit(demo);//提交任务
//        }
//
//        //3.关闭线程池
//        pool.shutdown();//缓和关闭


    }

}

class ThreadPoolDemo implements Runnable{

    private int i = 0;
    @Override
    public void run() {

        while(i < 100){
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}