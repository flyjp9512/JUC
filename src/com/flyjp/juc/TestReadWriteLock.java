package com.flyjp.juc;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.ReadWriteLock : 读写锁
 * 写写/读写 需要”互斥“
 * 读读 不需要互斥
 */
public class TestReadWriteLock {

    public static void main(String[] args){
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.set(100);
            }
        },"Write").start();

        for(int i = 0 ; i < 100 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.get();
                }
            },"Read").start();
        }


    }

}

class ReadWriteLockDemo{
    private int number = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();//读写锁

    //读
    public void get(){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() +
                    ":" + number);
        }finally {
            lock.readLock().unlock();
        }

    }

    //写
    public void set(int number){
        lock.writeLock().lock();
        try{
            this.number = number;
            System.out.println(Thread.currentThread().getName() + ":"
            + number);
        }finally {
            lock.writeLock().unlock();
        }

    }
}
