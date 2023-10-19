package ueg.back.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ChatServer server;
    private DataOutputStream outputStream;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public ClientHandler(Socket clientSocket, ChatServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

            userName = inputStream.readUTF();

            while (true) {
                String message = inputStream.readUTF();
                server.broadcastMessage(message, this);
            }
        } catch (SocketException e) {
            System.out.println("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                server.removeClient(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMessage(String message, String userName) {
        try {
            if (!Objects.equals(userName, message)) {
                outputStream.writeUTF(userName);
                outputStream.flush();
                outputStream.writeUTF(message);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
