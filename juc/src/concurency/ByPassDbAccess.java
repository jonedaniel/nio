package concurency;

/**
 * Title: ByPassDbAccess
 * Description:
 *  circumstance: 70万条100字段以上数据
 *  question:
 *      1.单次查出来不仅速度很慢,而且会爆内存
 *      2.使用lambada parallelStream 只开系统内核的线程,只适合cpu密集型,不适合 web ,database 等io密集型
 *      3.使用本地LinkedQueue处理线程共享变量速度莫名其妙的慢,然后druid连接池报超时,怀疑事务原因,躲开切面还是超时.
 *      4. 使用redis 队列顺利解决. 10分钟级的问题变成1分钟.
 *      5.对于问题3怀疑是线程锁的消耗造成
 * @author zhaomenghui93@163.com
 * @createDate 2018/12/16
 */
public class ByPassDbAccess {
    public static void main(String[] args) {
        getSystemCoreNumber();
    }

    private static void getSystemCoreNumber() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
