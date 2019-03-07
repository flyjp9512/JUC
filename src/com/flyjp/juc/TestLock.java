package com.flyjp.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、用于解决多线程安全问题的方式：
 * synchronized:
 * 1.同步代码块
 *
 * 2.同步方法
 *
 * jdk 1.5后
 * 3.同步锁Lock
 * 注意：是一个显示锁，需要通过lock() 方法上锁，必须通过unlock()方法进行释放锁
 */
public class TestLock {
    public static void main(String[] args){
        Ticket ticket = new Ticket();
        new Thread(ticket,"1号：窗口").start();
        new Thread(ticket,"2号：窗口").start();
        new Thread(ticket,"3号：窗口").start();
    }
}

class Ticket implements  Runnable{

    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            lock.lock();//上锁
            try {
                if(tick > 0){
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().
                            getName() + "完成售票，余票："+ --tick);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();//释放锁
            }


        }
    }
}
