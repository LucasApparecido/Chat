package ueg.front;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private static Screen instance;

    public static final int width = 800;
    public static final int height = 600;

    public JTextField write;

    private Screen (){
        setTitle("Chat - User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Screen.width, Screen.height);
        setResizable(false);
        setLocation(100, 100);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        //buildLayout();

        setVisible(true);
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }

    private void buildLayout(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color (1,32,15));

        write = new JTextField();
        write.setFont(new Font("Times New Roman", Font.BOLD, 24));
        write.setHorizontalAlignment(JTextField.RIGHT);
        //setWriteSize(display, 300, 50);
        write.setBackground(new Color(1,32,15));
        write.setEnabled(false);
        mainPanel.add(write, BorderLayout.SOUTH);
    }

}