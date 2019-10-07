
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    private String conIP;
    private int conPort;
    private String userName;
    private Scanner scan = new Scanner(System.in);
    private String sentence;

    DatagramSocket clientSocket = new DatagramSocket();
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];

    public Client() throws IOException {
        clientAwake();
    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
    }

    public void clientAwake() throws IOException {

        System.out.println("Please enter the chatroom IP: 192.168.1.38");
        this.conIP = scan.nextLine();
        InetAddress IPAddress = InetAddress.getByName(conIP);
        System.out.println("Please enter port:");
        this.conPort = scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter username:");
        this.userName = scan.nextLine();
        this.sendData = userName.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, conPort);
        clientSocket.send(sendPacket);

    while (true) {
        System.out.println("Type Message:");
        this.sentence = scan.nextLine();
        if (sentence.equalsIgnoreCase("quit")) {
            clientSocket.close();
        }
        this.sendData = sentence.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, conPort);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        }
    }

    public String getConIP() {
        return conIP;
    }

    public void setConIP(String conIP) {
        this.conIP = conIP;
    }

    public int getConPort() {
        return conPort;
    }

    public void setConPort(int conPort) {
        this.conPort = conPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public DatagramSocket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public byte[] getSendData() {
        return sendData;
    }

    public void setSendData(byte[] sendData) {
        this.sendData = sendData;
    }

    public byte[] getReceiveData() {
        return receiveData;
    }

    public void setReceiveData(byte[] receiveData) {
        this.receiveData = receiveData;
    }
}
