package test.concurent_meta_sentence;

import java.util.concurrent.TimeUnit;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Runner runner1 = new Runner();
        Thread thread1 = new Thread(runner1);
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
        Runner runner  = new Runner();
        Thread thread2 = new Thread(runner);
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        runner.cancle();
        System.out.println(runner1.count + ":" + runner.count);
    }
}

class Runner implements Runnable{
    long count = 0;
    private volatile boolean on = true;
    @Override
    public void run() {
        while (on && !Thread.currentThread().isInterrupted()) {
            count++;
        }
    }

    public void cancle() {
        on = false;
    }
}

