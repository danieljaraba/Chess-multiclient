package Server.ui;

import Server.connection.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        TCPServer server = TCPServer.getInstance();
        server.start();

    }
}
