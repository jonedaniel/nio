package tcp;
import java.net.*;
import java.io.*;

public class TcpServer {

    public static void main(String[] args) throws Exception{
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connetionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connetionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connetionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            capitalizedSentence = clientSentence.toUpperCase() + "\n";
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
