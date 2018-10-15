package collectiont;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title: ConcurrentLinkedQueueTest
 * Description: 并发队列 适用于多生产者多消费者的情况下
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/10/12
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        Queue<String> queue = new ConcurrentLinkedQueue<>();
//        Queue<String> queue = new LinkedList<>();//使用这个list多线程访问下会出现死循环
        AtomicInteger j = new AtomicInteger(0);
        for (int i = 1; i <= 1000000; i++) {
            queue.add("hello");
        }
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
                j.getAndIncrement();
            }
        }).start();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
                j.getAndIncrement();
            }
        }).start();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
                j.getAndIncrement();
            }
        }).start();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
                j.getAndIncrement();
            }
        }).start();
        while (!queue.isEmpty()) {
            Thread.sleep(1);
        }
        System.out.println(">>>>>>>>>>>>>"+String.valueOf(System.currentTimeMillis()-now));
        System.out.println(j.get());
    }

}
