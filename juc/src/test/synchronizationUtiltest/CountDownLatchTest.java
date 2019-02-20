package test.synchronizationUtiltest;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            new Thread1(latch).start();
        }
    }
}

class Thread1 extends Thread {
    CountDownLatch countDownLatch;

    Thread1(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " is running ,countDownLatch is " + countDownLatch.getCount());
        countDownLatch.countDown();
        try {
            countDownLatch.await();// 等待为0,一起执行
//            countDownLatch.await(1, TimeUnit.SECONDS); 等待1秒, 如果countDown还不为0,则继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName() + " waiting accomplish ,countDownLatch is " + countDownLatch.getCount());

    }
}
