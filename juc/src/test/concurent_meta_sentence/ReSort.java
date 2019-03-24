package test.concurent_meta_sentence;

public class ReSort {
    int     a    = 0;
    boolean flag = false;

    void add() {
        a = 2;
        flag = true;
    }

    void substract() {
        a = 0;
        flag = false;
    }

    void reader() {
        if (flag) {
            int i = a * a;
            System.out.println(flag + ":" + i);
        } else {
            System.out.println(flag + ":" + a);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReSort re = new ReSort();

    }


}
