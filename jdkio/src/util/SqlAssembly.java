package util;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;
import java.util.Date;

public class SqlAssembly {
    public static void main(String[] args) throws IOException {
        rowRw();
    }

    private static void rowRw() throws IOException {
        FileReader     reader = new FileReader("E:\\picname.txt");
        BufferedReader br     = new BufferedReader(reader);


        StringBuilder sb  = new StringBuilder("");
        String         str    = null;
        StringBuilder begin  = new StringBuilder("insert into hdic.t_hm_resblock_file(file_id,resblock_id,file_url,file_type,create_by,create_time) values");

        int            count     = 0;
        FileWriter     writer    = new FileWriter("E:\\picname"+".sql");
        BufferedWriter bw        = new BufferedWriter(writer);

        while ((str = br.readLine()) != null) {
            String[] split = str.split(",");
            String resblockName = split[0].split("/")[0];
            String fileId = "fileId";
            String resblockId = "resblockId";
            begin.append("(")
                    .append(fileId).append(",")
                    .append(resblockId).append(",")
                    .append(split[1]).append(",")
                    .append("135136").append(",")
                    .append(new Date().toString()).append(")");
        }
        bw.write(begin.toString());
        br.close();
        reader.close();
        bw.close();
        writer.close();
    }
}
