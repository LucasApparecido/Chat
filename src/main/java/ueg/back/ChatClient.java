package ueg.back;

import ueg.back.Messages.AddMessage;
import ueg.back.Messages.MessageObserver;
import ueg.front.Screen;

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


    private List<MessageObserver> observers = new ArrayList<>();

    private Screen screen;

    private static ChatClient instance;

    private ChatClient(String serverAddress, int port, String userName) {
        try {
            socket = new Socket(serverAddress, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());

            screen = Screen.getInstance(userName);
            addObserver(screen);

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

    public void addObserver(MessageObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String userName, String message) {
        for (MessageObserver observer : observers) {
            observer.update(userName, message);
        }
    }


    private void listenForMessages() {
        try {
            while (true) {
                String userName = inputStream.readUTF();
                String message = inputStream.readUTF();
                notifyObservers(userName, message);
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
