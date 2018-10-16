package stream;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTest {
    @Test
    public void mapTo() {
        List<Pojo> list = new ArrayList<>();
        list.add(new Pojo(1));
        list.add(new Pojo(1));
        list.add(new Pojo(1));
        list.add(new Pojo(1));
        list.add(new Pojo(1));
        list.add(new Pojo(1));
        list.add(new Pojo(1));
        list.add(new Pojo(1));

        StreamTest test = new StreamTest();

//        System.out.println(list.stream().mapToInt(test::add).sum());
        System.out.println(list.stream().mapToInt(o -> {
            test.otherOpt();
            return test.add(o);
        }).sum());
    }

    private int add(Pojo pojo) {
        return pojo.x;
    }

    private void otherOpt() {
        System.out.println("hello world");
    }

    @Test
    public void commonUsage() {
        Optional<List> optional = Optional.ofNullable(new Pojo().words);
        System.out.println(optional.isPresent() ? optional.get().get(0) : "nothing");
    }

    @Test
    public void optionalApi() {
        Pojo pojo1 = null;
        Pojo pojo2 = new Pojo(2, "fuck off");
        Pojo pojo3 = Optional.ofNullable(pojo1).orElse(pojo2);
        System.out.println(pojo3);
    }


    /**
     * 在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
     *
     * @createDate 2018/9/16
     */
    @Test
    public void differenceBetweenOrElseAndOrElseGet() {
        Pojo pojo = new Pojo(1, "fucking");
        System.out.println("一：使用api获取新对象");
        Pojo newPojo = Optional.ofNullable(pojo).orElse(newPojo());
        System.out.println("二：使用api获取新对象");
        pojo = Optional.ofNullable(pojo).orElseGet(() -> newPojo());
    }

    private Pojo newPojo() {
        System.out.println("创建了新对象");
        return new Pojo(0, "fuck you");
    }

    @Test
    public void withMap() {
        Pojo pojo = new Pojo(1, "go to the hell");
        List<String> words = Optional.ofNullable(pojo)
                .map(p -> p.getWords()).orElseGet(() -> new ArrayList<>());
        Assert.assertNotNull(words);
    }

    @Test
    public void withFilter() {
        Pojo pojo = new Pojo(3, "fucker");

        Optional<Pojo> optionalPojo = Optional.ofNullable(pojo).filter(p -> p.getWords() != null && p.getWords().size() > 0);
        System.out.println(optionalPojo.isPresent());
    }

    @Test
    public void mapToQueue() {
        List<String> collect = Arrays.stream(new String[]{"hello", "world", "hello", "world", "hello", "world"})
                .filter(str->!str.equals("zmh"))
                .collect(Collectors.toList());
        System.out.println(collect);
        String[] strings = collect.stream()
                .map(s -> s.replace("l", ""))
                .filter(s -> !s.contains("r"))
                .toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
    }
}


