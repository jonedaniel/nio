package codeSrc;

import java.util.ArrayList;
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

    private static CopyOnWriteArrayList<Integer> pojos  = new CopyOnWriteArrayList<>();
    private static List<Integer>                 pojos2 = new ArrayList<>();

    public static void main(String[] args) {
        CopyOnWriteArrayTest test1 = new CopyOnWriteArrayTest();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " 执行第: " + i + "次");
                test1.listAdd(1);

            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " 执行第: " + i + "次");
                test1.listAdd(2);
            }
        }).start();

    }

    private void listAdd(Integer val) {
        pojos.add(val);
        System.out.println(pojos);
//        pojos2.add(val);
//        System.out.println(pojos2);
    }
}
