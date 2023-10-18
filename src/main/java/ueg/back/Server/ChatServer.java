package ueg.back.Server;

import java.io.DataOutputStream;
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

    private void updateUsersList(DataOutputStream outputStream) {
        List<String> userNames = new ArrayList<>();
        for (ClientHandler client : clients) {
            userNames.add(client.getUserName());
        }
        broadcastUsersList(userNames, outputStream);
    }

    public synchronized List<String> getUserNames() {
        List<String> userNames = new ArrayList<>();
        for (ClientHandler client : clients) {
            userNames.add(client.getUserName());
        }
        return userNames;
    }

    public synchronized void removeClient(ClientHandler client) {
        clients.remove(client);
        updateUsersList(client.getOutputStream());
    }

    private void broadcastUsersList(List<String> userNames, DataOutputStream outputStream) {
        try {
            outputStream.writeUTF("Todos");
            outputStream.writeInt(userNames.size());
            for (String userName : userNames) {
                if (userName != null) {
                    outputStream.writeUTF(userName);
                }
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addClient(ClientHandler client) {
        clients.add(client);
        updateUsersList(client.getOutputStream());
        client.sendUsersList(getUserNames());
        broadcastUpdatedUsersList();
    }

    private void broadcastUpdatedUsersList() {
        for (ClientHandler client : clients) {
            client.sendUsersList(getUserNames());
        }
    }


    public static void main(String[] args) {
        ChatServer server = new ChatServer(12345);
        server.start();
    }
}