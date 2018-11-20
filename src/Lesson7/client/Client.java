package Lesson7.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {

    private boolean isAuthorized;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8686;

    JPanel authPanel;
    JPanel bottomPanel;
    JPanel centerPanel;
    JTextArea textArea;
    JTextField textField;
    JTextField loginField;
    JPasswordField passwordField;

    Client() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("img/chat.png");
        setIconImage(icon.getImage());
        setBounds(500, 300, 500, 400);

        authPanel = new JPanel();
        //authPanel.setLayout(new BoxLayout(authPanel, BoxLayout.Y_AXIS));
        JLabel loginLabel = new JLabel("Логин");
        loginField = new JTextField(7);
        JLabel pwdLabel = new JLabel("Пароль");
        passwordField = new JPasswordField(7);
        JButton authButton = new JButton("Авторизоваться");
        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(pwdLabel);
        authPanel.add(passwordField);
        authPanel.add(authButton);

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tryToAuth();
            }
        });

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tryToAuth();
            }
        });


        bottomPanel = new JPanel();
        centerPanel = new JPanel();

        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setPreferredSize(new Dimension(500, 40));
        bottomPanel.setVisible(false);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(authPanel, BorderLayout.NORTH);


        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        textArea = new JTextArea();
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 30));
        JButton button = new JButton("Send");
        bottomPanel.add(textField);
        bottomPanel.add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMsg();
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMsg();
            }
        });

        setVisible(true);

    }

    private void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.setText(null);
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryToAuth() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + String.valueOf(passwordField.getPassword()));
            loginField.setText(null);
            passwordField.setText(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                textArea.append(str + "\n");
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverclosed")) {
                                break;
                            }
                            textArea.append(str + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            authPanel.setVisible(true);
            bottomPanel.setVisible(false);
        } else {
            authPanel.setVisible(false);
            bottomPanel.setVisible(true);
        }
    }
}
