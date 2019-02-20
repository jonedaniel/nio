package test.synchronizationUtiltest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        // when the 3 parties invoke together,they will go to the next together.
        // and the cyclicBarrier can use again
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 6; i++) {
            new Thread2(cyclicBarrier).start();
        }
    }
}

class Thread2 extends Thread {
    CyclicBarrier cyclicBarrier;

    Thread2(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + " begin to running");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread.getName() + " is accomplish,running again");
    }
}
