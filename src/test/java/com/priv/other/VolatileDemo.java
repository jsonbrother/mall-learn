package com.priv.other;

/**
 * @author Json
 * @date 2021/5/28 9:54
 */
public class VolatileDemo {


    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("########" + threadDemo.isFlag());
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private boolean flag = false;

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        System.out.println("flag=" + isFlag());
    }

    boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
