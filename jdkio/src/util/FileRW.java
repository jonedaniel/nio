package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileRW {
    public static void main(String[] args) throws Exception {
        System.out.println();
        String     userDir    = System.getProperty("user.dir");
        File       file       = new File(userDir + "/jdkio/out/util/temp.json");
        File       retFile    = new File("ret.json");
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(retFile);

//        char[] buffer = new char[1024];

        int           length;
        StringBuilder sb = new StringBuilder(100);
        while ((length = fileReader.read()) != -1) {
            char temp = (char) length;
            if (!(temp == '\n' || temp == '\t' || temp == '\r' || temp == '\b' || temp == '\0' || length == 32)) {
                sb.append(String.valueOf(temp));
            }
        }
        String s = new String(sb);
        System.out.println(s);
    }
}
