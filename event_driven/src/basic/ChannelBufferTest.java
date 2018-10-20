package basic;

import javax.naming.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Title: SelectorTest
 * Description: selector测试
 *
 * @author zhaomenghui
 * @createDate 2018/10/19
 * @version 1.0
 */
public class ChannelBufferTest {
    public static void main(String[] args) throws IOException {
//        channelIO();
        channelTrans();
    }

    private static void channelIO() throws IOException {
        RandomAccessFile aFile     = new RandomAccessFile("nio-data.txt", "rw");

        FileChannel      inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            System.out.println();

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    private static void channelTrans() throws IOException {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel      fromChannel = file.getChannel();
        RandomAccessFile file1 = new RandomAccessFile("toChannel.txt", "rw");
        FileChannel      toChannel = file1.getChannel();
        toChannel.transferFrom(fromChannel, 0, fromChannel.size());
    }
}
