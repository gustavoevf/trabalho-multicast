import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class Multicast {
    private MulticastSocket socket;
    private InetAddress groupIp = null;
    private InetSocketAddress group = null;
    private String room;
    private int port = 6789;

    public Multicast(String room) throws IOException {
        this.room = room;
    }

    public boolean enterRoom(String userName) throws IOException {
        try {
            groupIp = InetAddress.getByName(room);
            group = new InetSocketAddress(groupIp, port);            
            socket = new MulticastSocket(6789);
            socket.joinGroup(group, null);
            System.out.println("Entrou do grupo multicast " + room);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean leaveRoom() throws IOException {
        try {
            socket.leaveGroup(group, null);
            if (socket != null)
                socket.close();
            return true;
        } catch (IOException ex) {
            throw ex;
        }
    }

    void sendMessage(String text) throws IOException {
        try {
            byte[] message = text.getBytes();
            DatagramPacket messageOut = new DatagramPacket(message, message.length, groupIp, 6789);
            socket.send(messageOut);
        } catch (IOException ex) {
            throw ex;
        }
    }

    void receiveMessages() throws IOException {
        try {
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                System.out.println("Recebido:" + new String(messageIn.getData()).trim());
                buffer = new byte[1000];
            }
        } catch (IOException ex) {
            throw ex;
        }
    }

}
