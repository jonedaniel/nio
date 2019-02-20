package basic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.State.*;
import static java.util.Arrays.asList;

/**
 * Title: ThreadStatus
 * Description: 线程状态演示
 * 线程的状态包括: 新建NEW,就绪RUNNABLE,运行RUNNING,阻塞BLOCKED,终结TERMINATED
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2019/2/20
 */
public class ThreadStatusDisplay {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);// 3. BLOCKED
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(
                    Thread.currentThread().getName() + "is running" +
                            "  " + (Thread.currentThread().isDaemon() ? "是守护线程" : "非守护线程")
            );
            //执行完后:  TERMINATED
        });//1.NEW
        thread1.start();//2. RUNNING
        System.out.println(!thread1.isAlive() ? "已终结" : "未终结");
        thread1.join(3000);//wait 3000 milliseconds to die
        System.out.println(!thread1.isAlive() ? "已终结" : "未终结");
        Thread.sleep(6000);//
        System.out.println(!thread1.isAlive() ? "已终结" : "未终结");
        System.out.println("主线程是"+(Thread.currentThread().isDaemon() ? "是守护线程" : "非守护线程"));
    }

    private void threadStatusEnum() {
        List<Thread.State> list = new ArrayList<>(
                asList(NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING)
        );
        System.out.println(list);
    }


}
