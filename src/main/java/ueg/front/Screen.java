package ueg.front;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private static Screen instance;

    public static final int width = 800;
    public static final int height = 600;

    private JTextField write;

    private JButton send;

    private JPanel mainPanel;
    private JPanel writeGroup;
    private JPanel usersPanel;

    private JList<String> usersList;

    private JTextArea reply;

    private JScrollPane scroll;

    private String teste = "teste";

    String[] users = {"User 1", "User 2", "User 3"};


    private Screen (){
        setTitle("Chat - User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Screen.width, Screen.height);
        setResizable(false);
        setLocation(100, 100);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        buildLayout();

        setVisible(true);
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }

    private void buildLayout() {
        mainPanel = new JPanel(new BorderLayout());

        writeGroup = new JPanel(new BorderLayout());
        write = new JTextField();
        write.setFont(new Font("Times New Roman", Font.BOLD, 24));
        write.setHorizontalAlignment(JTextField.RIGHT);
        write.setPreferredSize(new Dimension(300, 50));
        write.setBackground(Color.GRAY);
        writeGroup.add(write, BorderLayout.CENTER);

        send = new JButton("Enviar");
        writeGroup.add(send, BorderLayout.EAST);

        mainPanel.add(writeGroup, BorderLayout.SOUTH);

        usersPanel = new JPanel(new BorderLayout());
        usersPanel.setPreferredSize(new Dimension(150, 600));
        usersPanel.setBackground(Color.BLACK);

        usersList = new JList<>(users);
        usersList.setBackground(Color.BLACK);
        usersList.setForeground(Color.WHITE);
        usersList.setFont(new Font("Times New Roman", Font.BOLD, 24));

        
        usersPanel.add(usersList, BorderLayout.CENTER);
        mainPanel.add(usersPanel, BorderLayout.WEST);

        reply = new JTextArea();
        reply.setEditable(false);
        reply.setFont(new Font("Times New Roman", Font.BOLD, 24));
        reply.setBackground(Color.GRAY);
        reply.setForeground(Color.WHITE);

        scroll = new JScrollPane(reply);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scroll, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }


}