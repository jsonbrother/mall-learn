package com.priv.other;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Json
 * @date 2021/5/28 13:13
 */

public class AtomicDemo {

    public static void main(String[] args) {

        ThreadDemo2 threadDemo = new ThreadDemo2();

        for (int i = 0; i < 10; i++) {
            new Thread(threadDemo).start();
        }
    }
}

class ThreadDemo2 implements Runnable {

    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {

        }

        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    private int getSerialNumber() {
        // 自增运算
        return serialNumber.getAndIncrement();
    }
}
