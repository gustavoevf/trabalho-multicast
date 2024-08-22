import java.net.MulticastSocket;

public class ChatApp {
    public static void main(String[] args) throws Exception {
        IOUtil io = new IOUtil();
        io.writeLine("Olá, digite seu nome de usuário:");
        String userName =  io.readString();
        io.writeLine("Olá " + userName + ", digite a sala que deseja entrar:");
        String room = io.readString();

        Multicast sala = new Multicast(room);

    }
}
