package taskt;

import util.ThreadUtil;

import java.util.concurrent.*;

/**
 * Title: ExcecutorTest
 * Description: 线程池深入
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/10/15
 */
public class ExcecutorTest {
    public static void main(String[] args) throws Exception{
//        threadPoolTest();
//        futureTest();
    }

    private static void threadPoolTest() {
//        ExecutorService    executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newCachedThreadPool(ThreadUtil.getThreadFactory());
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
//        ExecutorService executorService = Executors.newSingleThreadExecutor(ThreadUtil.getThreadFactory());
        ExecutorService executorService = Executors.newWorkStealingPool(5);
        Thread          thread          = Executors.defaultThreadFactory().newThread(ThreadUtil.helloThread());
        executorService.submit(thread);
        executorService.submit(thread);
        executorService.submit(thread);
        executorService.submit(thread);
        executorService.submit(thread);
        executorService.submit(thread);
        executorService.submit(thread);
    }

    private static void futureTest() throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hello callable";
        };
        Future<String> future = executorService.submit(callable);
        while (!future.isDone()) {
            Thread.sleep(1000);
            System.out.println("wait for one second");
        }
        System.out.println(future.get(1,TimeUnit.SECONDS));

    }

}
