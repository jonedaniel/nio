package util;

import java.io.*;

public class FileRW {
    public static void main(String[] args) throws Exception {
        String     userDir    = System.getProperty("user.dir");
        File       file       = new File(userDir + "/jdkio/out/util/temp.json");
        FileReader fileReader = new FileReader(file);

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

        FileWriter   fileWriter = new FileWriter(userDir + "/jdkio/temp2.json");
        fileWriter.write(s);

        fileReader.close();
        fileWriter.close();
    }
}
