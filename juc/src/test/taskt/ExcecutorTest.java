package test.taskt;

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
    public static void main(String[] args) throws Exception {
//        threadPoolTest1();
//        threadPoolTest2();
//        futureTest();
    }

    private static void threadPoolTest1() {
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

    private static void threadPoolTest2() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        executor.execute(ThreadUtil.waitingThread(1000));
        while (executor.getQueue().size() > 0) {
            Thread.sleep(1000);
            System.out.println("主线程休息一秒");
        }
        System.out.println("主线程完了");
        executor.shutdown();

    }

    /**
     * callable future配合可以获取线程执行结果
     *
     * @author zhaomenghui
     * @createDate 2018/10/16
     */
    private static void futureTest() throws Exception {
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
        System.out.println(future.get(1, TimeUnit.SECONDS));

    }

}
