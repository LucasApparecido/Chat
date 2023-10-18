package ueg;

import ueg.back.ChatClient;
import ueg.front.Screen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;

        SwingUtilities.invokeLater(() -> {
            String userName = JOptionPane.showInputDialog("Digite seu nome: ");
            ChatClient.getInstance(serverAddress, port, userName);
        });
    }
}
