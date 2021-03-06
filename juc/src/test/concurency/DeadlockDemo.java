package test.concurency;

/**
 * 死锁演示
 *
 * @createDate 2018/11/24
 */
public class DeadlockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadlockDemo().deadLock();
    }

    private void deadLock() {
        Thread thread1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.currentThread().sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
