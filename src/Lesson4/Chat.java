package Lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Chat extends JFrame {

    Chat() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("img/chat.png");
        setIconImage(icon.getImage());
        setBounds(500, 300, 400, 400);

        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        bottomPanel.setBackground(Color.LIGHT_GRAY);

        bottomPanel.setPreferredSize(new Dimension(400, 40));

        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        JButton button = new JButton("Send");
        bottomPanel.add(textField);
        bottomPanel.add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.append(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                        + " 'My Name': " + textField.getText() + "\n");
                textField.setText(null);
                textField.requestFocus();
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.append(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                        + " 'My Name': " + textField.getText() + "\n");
                textField.setText(null);
                textField.requestFocus();
            }
        });

        setVisible(true);

    }
}

class MainChat {
    public static void main(String[] args) {
        new Chat();
    }
}
