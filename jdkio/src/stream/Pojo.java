package stream;

import java.util.List;

public class Pojo {
    int x;
    String tell;
    List<String> words;

    Pojo() {
    }

    Pojo(int x) {
        this.x = x;
    }

    Pojo(int x, String tell) {
        this.x = x;
        this.tell = tell;
    }

    public Pojo(int x, String tell, List<String> words) {
        this.x = x;
        this.tell = tell;
        this.words = words;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "x=" + x +
                ", tell='" + tell + '\'' +
                ", words=" + words +
                '}';
    }
}