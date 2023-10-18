package ueg.back;

import ueg.front.Screen;

import javax.management.DescriptorAccess;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    private Screen screen;

    private static ChatClient instance;

    private String userName;

    private ChatClient(String serverAddress, int port, String userName) {
        try {
            this.userName = userName;
            socket = new Socket(serverAddress, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());

            screen = Screen.getInstance(userName);

            outputStream.writeUTF(userName);
            outputStream.flush();


            new Thread(this::listenForMessages).start();

            System.out.println("Conectado ao servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ChatClient getInstance(String serverAddress, int port, String userName) {
        if (instance == null) {
            instance = new ChatClient(serverAddress, port, userName);
        }
        return instance;
    }

    private void listenForMessages() {
        try {
            while (true) {
                String identifier = inputStream.readUTF();
                if (identifier.equals("Todos")) {
                    handleUsersList(inputStream);
                } else if (identifier.equals("AtualizarUsuarios")) {
                    requestUpdatedUsersList();
                } else {
                    String userName = identifier;
                    String message = inputStream.readUTF();
                    AddMessage addMessage = new AddMessage();
                    addMessage.addMessage(message, userName, false);
                    System.out.println("Mensagem recebida");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestUpdatedUsersList() {
        sendUpdatedUsersList();
    }


    public void sendMessage(String message, String userName) {
        try {
            outputStream.writeUTF(userName);
            outputStream.flush();
            outputStream.writeUTF(message);
            outputStream.flush();
            System.out.println("Mensagem enviada");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUsersList(DataInputStream inputStream) {
        try {
            int numUsers = inputStream.readInt();
            List<String> userNames = new ArrayList<>();
            for (int i = 0; i < numUsers; i++) {
                String userName = inputStream.readUTF();
                userNames.add(userName);
            }
            handleUsersListUpdate(userNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleUsersListUpdate(List<String> userNames) {
        DefaultListModel<String> usersListModel = screen.getUsersListModel();

        SwingUtilities.invokeLater(() -> {
            usersListModel.clear();
            usersListModel.addElement("Todos");
            for (String user : userNames) {
                usersListModel.addElement(user);
            }
        });
        screen.setUsersListModel(usersListModel);
    }

    // Adicione um método para enviar a lista de usuários atualizada para o servidor
    private void sendUpdatedUsersList() {
        try {
            outputStream.writeUTF("AtualizarUsuarios");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
