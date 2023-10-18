package ueg.front;

import ueg.back.AddMessage;
import ueg.back.ChatClient;
import ueg.front.Personalize.CustomListCellRenderer;

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
    private JList<String> usersList;
    private JPanel reply;
    private JScrollPane scroll;
    private JPanel chatPanel;
    private String userName;
    private JPanel usersPanel;

    private DefaultListModel<String> usersListModel;

    private JList userList;

    private Screen(String userName) {
        this.userName = userName;
        setTitle("Chat - " + userName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Screen.width, Screen.height);
        setResizable(false);
        setLocation(100, 100);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        buildLayout();

        setVisible(true);
    }

    public static Screen getInstance(String userName) {
        if (instance == null) {
            instance = new Screen(userName);
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
        write.setBackground(new Color(68, 161, 160));
        write.setForeground(new Color(255, 255, 250));
        writeGroup.add(write, BorderLayout.CENTER);

        send = new JButton();
        send.setBackground(new Color(13, 92, 99));
        send.setForeground(new Color(255, 255, 250));
        send.setIcon(new ImageIcon(getClass().getResource("/send.png")));
        send.setPreferredSize(new Dimension(50, 50));
        send.addActionListener(e -> sendMessage());
        writeGroup.add(send, BorderLayout.EAST);

        chatPanel.add(writeGroup, BorderLayout.SOUTH);

        reply = new JPanel();
        reply.setBackground(new Color(120, 205, 215));
        reply.setLayout(new BoxLayout(reply, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(reply);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        chatPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(chatPanel, BorderLayout.CENTER);


        usersPanel = new JPanel(new BorderLayout());
        usersPanel.setPreferredSize(new Dimension(150, 600));

        usersListModel = new DefaultListModel<>();
        usersListModel.addElement("Todos");
        usersList = new JList<>(usersListModel);
        usersList.setBackground(new Color(36, 123, 123));
        usersList.setForeground(new Color(255, 255, 250));
        usersList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        usersList.setCellRenderer(new CustomListCellRenderer());
        usersList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUser = usersList.getSelectedValue();
                if (selectedUser != null && !selectedUser.equals(userName)) {
                }
            }
        });

        usersPanel.add(usersList, BorderLayout.CENTER);
        mainPanel.add(usersPanel, BorderLayout.WEST);

        getContentPane().add(mainPanel);
    }

    private void sendMessage() {
        String message = write.getText();
        write.setText("");
        if (!message.equals("")) {
            AddMessage addMessage = new AddMessage();
            addMessage.addMessage(message, userName, true);
            ChatClient server = ChatClient.getInstance("localhost", 12345, userName);
            server.sendMessage(message, userName);
        }
    }

    public JPanel getReply() {
        return reply;
    }


    public JScrollPane getScroll() {
        return scroll;
    }

    public void setReply(JPanel reply) {
        this.reply = reply;
    }

    public DefaultListModel<String> getUsersListModel() {
        return usersListModel;
    }

    public void setUsersListModel(DefaultListModel<String> usersListModel) {
        this.usersListModel = usersListModel;
    }
}