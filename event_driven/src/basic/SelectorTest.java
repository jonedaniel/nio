package basic;

import java.io.IOException;
import java.nio.channels.SelectionKey;

import static java.nio.channels.SelectionKey.*;

public class SelectorTest {
    public static void main(String[] args) throws IOException {
        interestSetOpTest();
    }

    private static void interestSetOpTest() throws IOException{
        int interestSet = OP_WRITE|OP_READ;
        int isInterestedInAccept  = interestSet & OP_ACCEPT;
        int isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
        int isInterestedInRead    = interestSet & SelectionKey.OP_READ;
        int isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;
        System.out.println(isInterestedInAccept
                +","+isInterestedInConnect
                +","+isInterestedInRead
                +","+isInterestedInWrite);
    }

}
