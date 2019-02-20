package test.synchronizationUtiltest;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        //acquire the semphore and release it
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread3(semaphore).start();
        }
    }
}

class Thread3 extends Thread {
    Semaphore semaphore;

    Thread3(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        String name          = currentThread.getName();
        System.out.println( name + " is running");
        try {
            semaphore.acquire();
            System.out.println(name + " acquire the semaphore");
            Thread.sleep(1000);
            semaphore.release();
            System.out.println(name + " hold the semaphore for 1 seconds and release it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}