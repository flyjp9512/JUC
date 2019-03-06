package com.flyjp.juc;

/**
 * volatile关键字
 */
public class TestVolatile {
    public static void main(String[] args){
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();
        while(true){
            if(demo.isFlag()){
                System.out.println("结束");
                break;
            }
        }
    }

}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {
    flag = true ;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    System.out.println("flag="+isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
