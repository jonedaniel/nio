package udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
    public static void main(String[] args)throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9999);
        byte[]         receivedata = new byte[1024];
        byte[]         sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receivedata, receivedata.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress ipAddr = receivePacket.getAddress();
            int port = receivePacket.getPort();
            System.out.println("FROM CLIENT:"+sentence+",ipaddr:"+ipAddr.toString()+",port:"+port);
            String capitalizedSentence = sentence.toUpperCase();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddr, port);
            serverSocket.send(sendPacket);
        }
    }
}
