package Server.connection;

import Server.model.Match;
import Server.model.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer extends Thread{

    public static TCPServer instance;

    public static TCPServer getInstance(){
        if(instance == null){
            instance = new TCPServer();
        }
        return instance;
    }

    public TCPServer(){
        matches = new ArrayList<>();
    }

    private ServerSocket svSocket;
    private ArrayList<Match> matches;

    @Override
    public void run() {
        try {
            this.svSocket = new ServerSocket(6060);
            while(true){
                System.out.println("Esperando conexion");
                Socket socket1 = svSocket.accept();
                System.out.println("Primer cliente conectado");
                Socket socket2 = svSocket.accept();
                System.out.println("Segundo cliente conectado");
                Match match = new Match(socket1, socket2, new Session(generateLetter()));
                matches.add(match);
                match.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char generateLetter(){
        double num = Math.random()*25;
        num += 66;
        int value = (int)num;
        char letter = (char) value;
        return letter;
    }
}
