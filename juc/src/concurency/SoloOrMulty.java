package concurency;

import java.util.concurrent.*;

/**
 * 上下文切换有时间消耗,小任务用单线程效率更高
 *
 * @createDate 2018/11/24
 */
public class SoloOrMulty {
    private static Long count = 900000000L;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        soloExec();
        multiExec();
//        new Thread(SoloOrMulty::multyExec2).start();
    }

    private static void multiExec() {
        try {
            long       current    = System.currentTimeMillis();
            FutureTask futureTask = new FutureTask<Integer>(SoloOrMulty::task1, 5);
            futureTask.run();
            Object o = futureTask.get();
            task1();
            long spends = System.currentTimeMillis() - current;
            System.out.println("多线程执行花费:" + spends);
        } catch (Exception ignored) {
        }
    }

    private static void multyExec2() {
        try {
            long   current = System.currentTimeMillis();
            Thread thread  = new Thread(SoloOrMulty::task1);
            thread.start();
            task1();
            long spends = System.currentTimeMillis() - current;
            thread.join();
            System.out.println("多线程2执行花费:" + spends);
        } catch (Exception ignored) {
        }
    }

    private static void soloExec() {
        long current = System.currentTimeMillis();
        task1();
        task1();
        long spends = System.currentTimeMillis() - current;
        System.out.println("单线程执行花费:" + spends);
    }

    private static void task1() {
        int b = 0;
        for (int i = 0; i < count; i++) {
            b += 5;
        }
    }
}
