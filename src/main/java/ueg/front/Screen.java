package ueg.front;

import ueg.back.Personalize.CustomListCellRenderer;

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

    private JPanel reply;

    private JScrollPane scroll;

    private String teste = "teste";

    private JPanel chatPanel;

    private JLabel sendLabel;

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
        chatPanel = new JPanel(new BorderLayout());

        writeGroup = new JPanel(new BorderLayout());
        write = new JTextField();
        write.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        write.setHorizontalAlignment(JTextField.LEFT);
        write.setPreferredSize(new Dimension(300, 50));
        write.setBackground(new Color(68,161,160));
        write.setForeground(new Color(255,255,250));
        writeGroup.add(write, BorderLayout.CENTER);


        send = new JButton();
        send.setBackground(new Color(13,92,99));
        send.setForeground(new Color(255,255,250));
        send.setIcon(new ImageIcon(getClass().getResource("/send.png")));
        send.setPreferredSize(new Dimension(50, 50));
        writeGroup.add(send, BorderLayout.EAST);

        chatPanel.add(writeGroup, BorderLayout.SOUTH);

        reply = new JPanel();
        reply.setBackground(new Color(120,205,215));


        scroll = new JScrollPane(reply);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        chatPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(chatPanel, BorderLayout.CENTER);

        usersPanel = new JPanel(new BorderLayout());
        usersPanel.setPreferredSize(new Dimension(150, 600));

        usersList = new JList<>(users);
        usersList.setBackground(new Color(36,123,123));
        usersList.setForeground(new Color(255,255,250));
        usersList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        usersList.setCellRenderer(new CustomListCellRenderer());

        usersPanel.add(usersList, BorderLayout.CENTER);
        mainPanel.add(usersPanel, BorderLayout.WEST);

        getContentPane().add(mainPanel);
    }

    public JPanel getReply() {
        return reply;
    }

    public JTextField getWrite() {
        return write;
    }

    public JList<String> getUsersList() {
        return usersList;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setReply(JPanel reply) {
        this.reply = reply;
    }

    public void setUsersList(JList<String> usersList) {
        this.usersList = usersList;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }
}