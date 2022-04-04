package Player.ui;

import Player.connection.TCPClient;
import Player.model.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Controller controller;

    public Main(){
        controller = new Controller();
    }

    public static void main(String[] args) {

        Main main = new Main();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana1.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        TCPClient client = TCPClient.getInstance();
        client.setReceiver(controller);
        client.start();
    }
}
