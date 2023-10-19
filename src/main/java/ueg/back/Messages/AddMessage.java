package ueg.back.Messages;

import ueg.front.Personalize.ColorDecorator;
import ueg.front.Personalize.MessageDecorator;
import ueg.front.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AddMessage {

    private Screen screen = Screen.getInstance("userName");

    private JPanel reply = screen.getReply();
    private JScrollPane scroll = screen.getScroll();

    private String prefix;

    private JLabel messageLabel;

    private JLabel messageLabelServer;

    private MessageDecorator decoratorColorU;
    private MessageDecorator decoratorColorC;
    private MessageDecorator decoratorColorS;

    private Color userMessageColor = new Color(128, 0, 128);
    private Color contactMessageColor = new Color(0, 0, 255);

    private Color serverMessageColor = Color.DARK_GRAY;

    public void addMessage(String message, String userName, Boolean writer) {
        mountMessage(message, userName, writer);
    }

    private void mountMessage(String message, String userName, Boolean writer) {

        prefix = userName + ": ";
        messageLabel = new JLabel(prefix + message);
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        if (writer) {
            decoratorColorU = new ColorDecorator(userMessageColor);
            decoratorColorU.decorate(messageLabel);
        } else {
            decoratorColorC = new ColorDecorator(contactMessageColor);
            decoratorColorC.decorate(messageLabel);
        }
        reply.add(messageLabel);


        screen.setReply(reply);
        scroll.revalidate();
        scroll.repaint();

    }
}
