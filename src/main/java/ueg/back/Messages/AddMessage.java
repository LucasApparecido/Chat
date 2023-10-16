package ueg.back.Messages;

import ueg.front.Screen;

import javax.swing.*;
import java.awt.*;

import static ueg.front.Screen.getInstance;

public class AddMessage {

    private Screen screen = getInstance();

    private JPanel reply = screen.getReply();
    private JScrollPane scroll = screen.getScroll();

    private String prefix;
    private String formattedMessage;

    private JLabel messageLabel;

    public void addMessage(String message, String nameUser, Boolean writer) {
        mountMessage(message, nameUser, writer);
    }

    private void mountMessage(String message, String nameUser, Boolean writer) {

        prefix = nameUser + ": ";
        formattedMessage = prefix + message;
        messageLabel = new JLabel(formattedMessage);
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        messageLabel.setForeground(Color.BLACK);

        // Defina o alinhamento baseado no remetente da mensagem
        if (writer) {
            messageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        } else {
            messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        // Adicione o JLabel ao JPanel
        reply.add(messageLabel);

        // Defina o layout do JPanel como BoxLayout para organizar as mensagens de baixo para cima
        reply.setLayout(new BoxLayout(reply, BoxLayout.Y_AXIS));

        // Atualize o JScrollPane
        scroll.revalidate();
        scroll.repaint();
    }
}
