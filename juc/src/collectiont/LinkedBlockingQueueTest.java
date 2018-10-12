package collectiont;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Title: LinkedBlockingQueueTest
 * Description: 阻塞队列阻塞功能测试
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/10/12
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            try {
                int i = 0;
                while (true) {
                    Thread.sleep(1000);
                    System.out.println(threadName + "存入元素");
                    queue.put("hello" + i++);
                    queue.put("world" + i++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            try {
                while (true) {
                    String take = queue.take();
                    System.out.println(threadName + "获取到元素：" + take);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
