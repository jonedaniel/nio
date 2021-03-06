package util;

import java.util.concurrent.ThreadFactory;

/**
 * Title: ThreadUtil
 * Description: 获取特定操作线程
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/10/12
 */
public class ThreadUtil {

    public static Thread helloThread() {
        return new Thread(() -> {
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 3; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(name + "执行第" + i + "次");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static ThreadFactory getThreadFactory() {
        return r -> ThreadUtil.helloThread();
    }

    public static Runnable waitingThread(int seconds) {
        return () -> {
            String name = Thread.currentThread().getName();
            try {
                Thread.sleep(seconds);
                System.out.println(name + "执行完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
