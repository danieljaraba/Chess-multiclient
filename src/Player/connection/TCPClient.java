package Player.connection;

import Player.model.MessageReceiver;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class TCPClient extends Thread{

    public static TCPClient instance;

    public static TCPClient getInstance(){
        if(instance == null){
            instance = new TCPClient();
        }
        return instance;
    }

    public TCPClient(){}

    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;
    private MessageReceiver receiver;

    @Override
    public void run() {
        try {
            socket = new Socket("localhost",6060);
            System.out.println("Conectado");
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String line = br.readLine();
                System.out.println(line);
                Platform.runLater(()->{
                    receiver.onMessage(line);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String line){
        new Thread(() ->{
            try {
                bw.write(line + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setReceiver(MessageReceiver receiver) {
        this.receiver = receiver;
    }
}
