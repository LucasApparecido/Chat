package ueg.back.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Porta 12345 aberta!");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente" + cliente.getInetAddress().getHostAddress());
                ServerThread thread = new ServerThread(cliente);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}