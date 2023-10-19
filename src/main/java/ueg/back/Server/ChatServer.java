package ueg.back.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();

    public ChatServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                addClient(clientHandler);

                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message, sender.getUserName());
            }
        }
    }

    public synchronized void addClient(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer(12345);
        server.start();
    }
}
