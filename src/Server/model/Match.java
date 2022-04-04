package Server.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;

public class Match extends Thread implements MessageReceiver{

    private Player player1;
    private Player player2;
    private Session session;
    private Gson gson;

    public Match(Socket s1, Socket s2, Session session){
        try {
            this.player1 = new Player(s1);
            player1.setReceiver(this);
            player1.start();
            this.player2 = new Player(s2);
            player2.setReceiver(this);
            player2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.session = session;
        this.gson = new Gson();
    }

    private void calculatePoints(){
        if(session.getName1().equalsIgnoreCase(session.getName2())){
            session.getPoints1()[0] = 50;
        }else{
            session.getPoints1()[0] = 100;
        }
        if(session.getAnimal1().equalsIgnoreCase(session.getAnimal2())){
            session.getPoints1()[1] = 50;
        }else{
            session.getPoints1()[1] = 100;
        }
        if(session.getCountry1().equalsIgnoreCase(session.getCountry2())){
            session.getPoints1()[2] = 50;
        }else{
            session.getPoints1()[2] = 100;
        }
        if(session.getThing1().equalsIgnoreCase(session.getThing2())){
            session.getPoints1()[3] = 50;
        }else{
            session.getPoints1()[3] = 100;
        }
        if(session.getName2().equals("")){
            session.getPoints2()[0] = 0;
        }else{
            session.getPoints2()[0] = session.getPoints1()[0];
        }
        if(session.getAnimal2().equals("")){
            session.getPoints2()[1] = 0;
        }else{
            session.getPoints2()[1] = session.getPoints1()[1];
        }
        if(session.getCountry2().equals("")){
            session.getPoints2()[2] = 0;
        }else{
            session.getPoints2()[2] = session.getPoints1()[2];
        }
        if(session.getThing2().equals("")){
            session.getPoints2()[3] = 0;
        }else{
            session.getPoints2()[3] = session.getPoints1()[3];
        }
    }

    @Override
    public void run() {
        String line = gson.toJson(session);
        System.out.println(line);
        player1.sendMessage(line);
        player2.sendMessage(line);
    }

    @Override
    public void onMessage(String line) {
        System.out.println(line);
        session = gson.fromJson(line, Session.class);
        if(session.getName1() != null && session.getName2() != null){
            calculatePoints();
        }
        player1.sendMessage(gson.toJson(session));
        player2.sendMessage(gson.toJson(session));
    }
}
