package collectiont;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Title: SynchronousQueueTest
 * Description: 同步队列，直接从生产者交给消费者，没有缓存
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/10/12
 */
public class SynchronousQueueTest {

    public static void main(String[] args) {
        BlockingQueue<String> queue0 = new SynchronousQueue();

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            int    count      = 0;
            while (true) {
                try {
                    System.out.println(threadName + "执行第" + count++ + "次");
                    queue0.put("hello");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            int    count      = 0;
            while (true) {
                try {
                    System.out.println(threadName + "执行第" + count++ + "次");
                    String take = queue0.take();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
