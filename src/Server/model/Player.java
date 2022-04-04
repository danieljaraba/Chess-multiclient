package Server.model;

import java.io.*;
import java.net.Socket;

public class Player extends Thread{

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private MessageReceiver receiver;

    public Player(Socket socket) throws IOException {
        this.socket = socket;
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            while (true){
                String line = br.readLine();
                receiver.onMessage(line);
            }
        } catch (IOException e) {
            System.out.println("Jugador desconectado");
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
