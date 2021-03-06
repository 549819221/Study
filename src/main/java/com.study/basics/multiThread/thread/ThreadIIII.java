package com.study.basics.multiThread.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 42.notify()和 notifyAll()有什么区别？
 */
public class ThreadIIII {
    /**
     * 等待池:假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁后，进入到了该对象的等待池中。等待池中的线程不会去竞争该对象的锁。
     * 锁池:只有获取了对象的锁，线程才能执行对象的 synchronized 代码，对象的锁每次只有一个线程可以获得，其他线程只能在锁池中等待。
     */

    /**
     * notify、notifyAll 的区别
     * 如果线程调用了对象的 wait()方法，那么线程便会处于该对象的等待池中，等待池中的线程不会去竞争该对象的锁。
     *
     * 当有线程调用了对象的 notifyAll()方法（唤醒所有 wait 线程）或 notify()方法（只随机唤醒一个 wait 线程），
     * 被唤醒的的线程便会进入该对象的锁池中，锁池中的线程会去竞争该对象锁。
     * 也就是说，调用了notify后只要一个线程会由等待池进入锁池，而notifyAll会将该对象等待池内的所有线程移动到锁池中，等待锁竞争。
     *
     * 优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，
     * 它还会留在锁池中，唯有线程再次调用 wait()方法，它才会重新回到等待池中。
     * 而竞争到对象锁的线程则继续往下执行，直到执行完了 synchronized 代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。
     */
    private static Object obj = new Object();

    public static void main(String[] args) {

        //测试 Thread1 wait()
        Thread t1 = new Thread( () -> {
            System.out.println("Thread1 开始执行");
            synchronized (obj) {
                System.out.println("Thread1 抢到了锁");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1被唤醒了拿到了锁");
            }
        });
        Thread t2 = new Thread( () -> {
            System.out.println("Thread2  开始执行");
            System.out.println("Thread2  睡眠3秒...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                System.out.println("Thread2 抢到了锁");
                System.out.println("Thread2 notify");
                obj.notify();
            }
        });

        //Thread2 notify()
        Thread t3 = new Thread( () -> {
            System.out.println("Thread3 开始执行");
            System.out.println("Thread3  睡眠3秒...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                System.out.println("Thread3 抢到了锁");
                System.out.println("notifyAll obj on Thread3");
                obj.notifyAll();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

}


