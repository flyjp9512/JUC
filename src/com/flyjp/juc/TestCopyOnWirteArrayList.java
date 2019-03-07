package com.flyjp.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWirteArrayList : “写入并复制”
 * 注意：添加(add)操作多时，效率低，因为每次添加时都会进行复制，并销非常大。
 *      并发迭代（iterator）操作多时可以选择。
 */
public class TestCopyOnWirteArrayList {
    public static void main(String[] args){
        HelloThread ht = new HelloThread();
        for(int i = 0 ; i < 10 ; i++) {
            new Thread(ht).start();
        }
    }
}

class HelloThread implements  Runnable{

    //private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    //并发修改异常
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");

    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            list.add("DD");
        }
    }
}

