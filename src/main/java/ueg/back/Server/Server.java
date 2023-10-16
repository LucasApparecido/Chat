package ueg.back.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private ServerThread serverThread;

    public Server() {

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor rodando na porta 12345");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
                serverThread = new ServerThread(socket);
                serverThread.start();
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}