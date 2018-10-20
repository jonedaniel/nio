package basic;

import javax.naming.Context;
import java.io.File;
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
        testChannel1();
    }

    private static void testChannel1() throws IOException {
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

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
