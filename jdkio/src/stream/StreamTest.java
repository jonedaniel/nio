package stream;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamTest {
    public static void main(String[] args) {

        List<X> list = new ArrayList<>();
        list.add(new X(1));
        list.add(new X(1));
        list.add(new X(1));
        list.add(new X(1));
        list.add(new X(1));
        list.add(new X(1));
        list.add(new X(1));
        list.add(new X(1));

        StreamTest test = new StreamTest();

//        System.out.println(list.stream().mapToInt(test::add).sum());
        System.out.println(list.stream().mapToInt(o -> {
            test.otherOpt();
            return test.add(o);
        }).sum());
    }

    private int add(X x) {
        return x.x;
    }

    private void otherOpt() {
        System.out.println("hello world");
    }

    @Test
    public void whatIsOptional() {
        Optional<X> optional = Optional.of(new X(10));
        System.out.println(optional.get().x);
    }

}


