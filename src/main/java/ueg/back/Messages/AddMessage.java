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

    private JLabel messageLabel;

    public void addMessage(String message, String nameUser) {
        mountMessage(message, nameUser);
    }

    private void mountMessage(String message, String nameUser) {
        prefix = nameUser + ": ";
        messageLabel = new JLabel(prefix + message);
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        messageLabel.setForeground(Color.BLACK);

        reply.add(messageLabel);

        reply.setLayout(new BoxLayout(reply, BoxLayout.Y_AXIS));

        scroll.revalidate();
        scroll.repaint();
    }
}
