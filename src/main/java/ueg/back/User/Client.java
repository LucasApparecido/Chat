package ueg.back.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {


    public static void run() throws UnknownHostException, IOException {

        //Cria um socket e conecta-o a um porta em host remoto.
        Socket socket = new Socket("127.0.0.1", 12345);

        //Fluxo de entrada e saida do socket
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        BufferedReader inTeclado = new BufferedReader(new InputStreamReader(System.in));

        String teclado = inTeclado.readLine();
        out.println("CLIENTE: envio de dado - "+ teclado);
        System.out.println(teclado);

        String fromServer = inServidor.readLine();
        System.out.println(fromServer);



    }


    public static void main(String[] args)throws UnknownHostException, IOException{
        Client.run();
    }


}