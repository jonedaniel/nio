package test.collection_util_t;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Title: CopyOnWriteArrayTest
 * Description: 并发集合测试
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/10/12
 */
public class CopyOnWriteArrayTest {

    private CopyOnWriteArrayList<String> list  = new CopyOnWriteArrayList<>();
    private List<String>                 list2 = new ArrayList<>();


    /**
     * copyOnWriteArray对数组进行操作后会刷新数组，避免ConcurrentModificationException
     *
     * @author zhaomenghui
     * @createDate 2018/10/12
     */
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayTest test       = new CopyOnWriteArrayTest();
        List<String>         initList   = test.list2;
        int                  circleTime = 100;//循环次数
        String               setVal0    = "first";//设置变量的值
        String               setVal1    = "second";//设置变量的值
        Thread               thread0    = test.getThread(initList, circleTime, setVal0);
        Thread               thread1    = test.getThread(initList, circleTime, setVal1);
        thread0.start();
        thread1.start();

        //检查元素数量是否正确
        Thread.sleep(1000 * 2);//等待两个线程执行完成
        int num1 = 0;
        while (initList.remove(setVal0))
            num1++;

        System.out.println(setVal0 + "数量：" + num1 + "|" + setVal1 + "数量：" + initList.size());//
    }

    private void listAdd(List list, String val) {
        list.add(val);
        System.out.println("list：" + list);//不在这个方法访问资源就不会报异常
    }

    private Thread getThread(List list, int circleTime, String setVal) {
        return new Thread(() -> {
            for (int i = 1; i <= circleTime; i++) {
                try {
                    String threadName = Thread.currentThread().getName();
                    listAdd(list, setVal);
                    System.out.println(threadName + " 执行第: " + i + "次");
                } catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                    return;//捕捉到异常就停止运行
                }
            }
        });
    }
}
