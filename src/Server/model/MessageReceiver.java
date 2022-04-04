package Server.model;

public interface MessageReceiver {

    void onMessage(String line);
}
