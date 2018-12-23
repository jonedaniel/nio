package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser   = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
        byte[]         sendData     = new byte[1024];
        byte[]         receivedata  = new byte[1024];
        String         sentence     = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9999);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receivedata, receivedata.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}
