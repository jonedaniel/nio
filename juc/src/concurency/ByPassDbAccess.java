package concurency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Title: ByPassDbAccess
 * Description:
 *  circumstance: 70万条100字段以上数据
 *  question:
 *      1.单次查出来不仅速度很慢,而且会爆内存
 *      2.使用lambada parallelStream 只开系统内核的线程,只适合cpu密集型,不适合 web ,database 等io密集型
 *      3.使用本地LinkedQueue处理线程共享变量速度莫名其妙的慢,然后druid连接池报超时,怀疑事务原因,躲开切面还是超时.
 *      4.使用redis 队列顺利解决. 10分钟级的问题变成1分钟.
 *  answer:
 *      1.发现在本地使用兵法队列时有问题,见queueBugExample
 * @author zhaomenghui93@163.com
 * @createDate 2018/12/16
 */
public class ByPassDbAccess {
    public static void main(String[] args) {
//        getSystemCoreNumber();
        queueBugExample();
    }

    private static void getSystemCoreNumber() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void queueBugExample() {
        List<Integer> list = new ArrayList(asList(1,2,3,4,5,6,7,8,9,10));
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue(list);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while (!queue.isEmpty()) {
            executorService.execute(()->theOtherThread(Optional.ofNullable(queue.poll()).orElse(0)));
        }
        executorService.shutdown();
    }

    private static void correctQueueUserExample() {
            List<Integer> list = new ArrayList(asList(1,2,3,4,5,6,7,8,9,10));
            ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue(list);
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            int size = queue.size();
            while (size-->0) {
                executorService.execute(()->theOtherThread(Optional.ofNullable(queue.poll()).orElse(0)));
            }
            executorService.shutdown();
    }

    private static void theOtherThread(int i) {
        System.out.println(i);
    }

}
