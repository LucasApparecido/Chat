package ueg.back.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{

            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            System.out.println("Antes do readline");
                String inputLine = in.readLine();
                System.out.println("Depois do readline");

                System.out.println(inputLine);
                out.println("SERVIDOR: dado recebido - "+inputLine);

        } catch(Exception e1){
            System.out.println(e1);
        }
    }
 
}

