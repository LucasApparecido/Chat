package ueg.back;

import ueg.front.Screen;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    private Screen screen;

    private static ChatClient instance;

    private ChatClient(String serverAddress, int port, String userName) {
        try {
            socket = new Socket(serverAddress, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());

            screen = Screen.getInstance(userName);

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
                String userName = (String) inputStream.readUTF();
                String message = (String) inputStream.readUTF();
                AddMessage addMessage = new AddMessage();
                addMessage.addMessage(message, userName, false);
                System.out.println("Mensagem recebida");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
