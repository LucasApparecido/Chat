package ueg;

import ueg.back.Messages.AddMessage;

import static ueg.front.Screen.getInstance;

public class Main {

    public static void main(String[] args) {
        //inicia o programa
        getInstance();

        AddMessage addMessage = new AddMessage();
        addMessage.addMessage("Mensagem", "Samuel");

    }
}