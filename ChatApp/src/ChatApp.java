
public class ChatApp {
    public static void main(String[] args) throws Exception {
        String userName =  args[0];
        String room = args[1];

        Multicast multicastRoom = new Multicast(room);
        boolean entrou = multicastRoom.enterRoom(userName);

        if (entrou) {
            multicastRoom.receiveMessages();
    
            boolean dentro = true; 
            while (dentro) {
                String message = IOUtil.readString();
                if("/sair".equals(message) || message == null) {
                    dentro = false;
                } else {
                    multicastRoom.sendMessage(userName + ": " + message);            
                }
            }

            multicastRoom.leaveRoom();
        }

    }
}
