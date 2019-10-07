import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.util.*;

public class Server
{
    private DatagramSocket serverSocket = new DatagramSocket(5555);
    private byte[] receiveData = new byte[1024];
    private byte[] sendData = new byte[1024];
    private List<String> usernames = new ArrayList<>();



    public Server() throws IOException {
        runServer();
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();
    }

    public void runServer() throws IOException {

        while(true)
            {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String userName = new String(receivePacket.getData());
            int port = receivePacket.getPort();
            SocketAddress ip = receivePacket.getSocketAddress();

            System.out.println("JOIN <<: " + userName);
            System.out.println("FROM PORT: " + port + " FROM THIS IP: " + ip);

            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();

            usernames.add(userName);
            System.out.println("LIST OF USERS <<" + usernames);

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
