package util;

import java.io.*;


/**
 * Title: ReadAndWrite
 * Description: 读写测试
 *
 * @author zhaomenghui
 * @createDate 2018/9/20
 * @version 1.0
 */
public class ReadAndWrite {

    public static void main(String[] args) throws Exception {
        rowRw();
    }

    private static void plainRw() throws IOException {
        FileInputStream  in  = new FileInputStream("F:/file.txt");
        FileOutputStream out = new FileOutputStream("F:/b.txt");

        //有可能会耗尽内存空间
        /*byte[] bytes = new byte[in.available()];
        int    res   = in.read(bytes);
        out.write(bytes);*/

        byte[] data = new byte[1024 * 8];
        int    res  = 0;
        while ((res = in.read(data)) != -1) {
            System.out.println(new String(data));
            out.write(data, 0, res);
        }

        in.close();
        out.close();
    }

    private static void rowRw() throws IOException {
        FileReader     reader = new FileReader("F:/csdn_600w.sql");
        BufferedReader br     = new BufferedReader(reader);


        StringBuilder sb  = new StringBuilder("");
        String         str    = null;
        String begin  = "insert into csdn(username,password,email) values(";

        int count = 0;
        int fileCount = 1;
        FileWriter writer = new FileWriter("F:/csdn/csdn"+fileCount+".sql");
        BufferedWriter bw = new BufferedWriter(writer);

        while ((str = br.readLine()) != null) {
            String[] split = str.split("#");
            if (!("".equals(split[0])||"".equals(split[1])||"".equals(split[2]))) {
                String result = begin+"'"+split[0]+"','"+split[1]+"','"+split[2]+"');\n";
//                bw.write(result);
                bw.write(str+";\n");
                count++;
                if (count%1000==0) {
                    fileCount++;
                    bw.flush();
                    writer = new FileWriter("F:/csdn/csdn"+fileCount+".sql");
                    bw = new BufferedWriter(writer);
                }
            }
        }

        br.close();
        reader.close();
        bw.close();
        writer.close();
    }


}
