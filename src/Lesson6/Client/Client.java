package Lesson6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRES = "localhost";
    final int PORT = 8686;

    public Client() {
        try {
            socket = new Socket(IP_ADDRES, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/server closed")) break;
                            System.out.println("Server: " + str);
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
            Scanner sc = new Scanner(System.in);
            while (true) {
                String str = sc.nextLine();
                out.writeUTF(str);
                if (str.equals("/end")) break;

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
}
