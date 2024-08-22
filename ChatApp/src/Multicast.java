import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class Multicast {
    private MulticastSocket socket;
    private InetAddress groupIp = null;
    private InetSocketAddress group = null;
    private final String room;
    private String userName;
    private final int port = 6789;
    private boolean running = true;

    public Multicast(String room) throws IOException {
        this.room = room;
    }

    public boolean enterRoom(String userName) throws IOException {
        try {
            this.userName = userName;
            groupIp = InetAddress.getByName("225.0.0." + room);
            group = new InetSocketAddress(groupIp, port);            
            socket = new MulticastSocket(6789);
            socket.joinGroup(group, null);
            sendMessage(userName + " entrou na sala");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean leaveRoom() throws IOException {
        try {
            sendMessage(userName + " saiu da sala");
            running = false;
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

    void receiveMessages() {
        new Thread() {
            @Override
            public void run () {
                try {
                    byte[] buffer = new byte[1000];
                    while (!Thread.currentThread().isInterrupted() && running) {
                        DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                        socket.receive(messageIn);
                        System.out.println(new String(messageIn.getData()).trim());
                        buffer = new byte[1000];
                    }

                    leaveRoom();
                } catch (IOException ex) {
                }
            }
        }.start();        
    }
}
