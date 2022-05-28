package cn.kai.mianshiTest.ListDome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListDome_01 {
    public static void main(String[] args) throws InterruptedException {
//        MyArrayList list = new MyArrayList();
//        for (int i = 0; i < 12; i++) {
//            list.add(""+i);
//        }
//
//        for (int i = 0; i < list.getSize(); i++) {
//            System.out.print(list.get(i)+" ");
//        }
        ExecutorService e1 =  Executors.newCachedThreadPool();
        ExecutorService e2 =  Executors.newFixedThreadPool(10);
        ExecutorService e3 =  Executors.newSingleThreadExecutor();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                10,20,10,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 1; i <= 100; i++) {
            e1.execute(new Task(i));
        }
        Thread.sleep(100000);
        for (int i = 101; i <= 200; i++) {
            e1.execute(new Task(i));
        }
    }
}

class Task implements Runnable{

    private int i;
    public Task(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"----------"+i);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
