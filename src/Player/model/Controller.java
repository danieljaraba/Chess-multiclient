package Player.model;

import Player.connection.TCPClient;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller implements MessageReceiver{

    private Gson gson;

    private Session session;

    private String identifier;

    @FXML
    private TextField animalAnswer;

    @FXML
    private TextField locationAnswer;

    @FXML
    private TextField nameAnswer;

    @FXML
    private TextField objectAnswer;

    @FXML
    private Button stopBtn;

    @FXML
    private Label title;

    @FXML
    private Button finishBtn;

    @FXML
    private Label opponentAnimalResult;

    @FXML
    private Label opponentLocationResult;

    @FXML
    private Label opponentNameResult;

    @FXML
    private Label opponentObjectResult;

    @FXML
    private Label ownAnimalResult;

    @FXML
    private Label ownLocationResult;

    @FXML
    private Label ownNameResult;

    @FXML
    private Label ownObjectResult;

    @FXML
    void stopAct(ActionEvent event) {
        if(!nameAnswer.getText().equals("") && !animalAnswer.getText().equals("") && !locationAnswer.getText().equals("") && !objectAnswer.getText().equals("")){
            this.session.setName1(nameAnswer.getText());
            this.session.setAnimal1(animalAnswer.getText());
            this.session.setCountry1(locationAnswer.getText());
            this.session.setThing1(objectAnswer.getText());
            String line = gson.toJson(session);
            System.out.println(line);
            TCPClient.getInstance().sendMessage(line);
            identifier = "player1";
        }
    }

    private void didnotStop(){
        this.session.setName2(nameAnswer.getText());
        this.session.setAnimal2(animalAnswer.getText());
        this.session.setCountry2(locationAnswer.getText());
        this.session.setThing2(objectAnswer.getText());
        String line = gson.toJson(session);
        System.out.println(line);
        TCPClient.getInstance().sendMessage(line);
        identifier = "player2";
    }

    private void showResults(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Player/ui/Ventana2.fxml"));
        loader.setController(this);
        try {
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPoints(){
        if(identifier.equals("player1")){
            int[] own = session.getPoints1();
            int[] opponent = session.getPoints2();
            ownNameResult.setText(session.getName1() + "(" + own[0] + ")");
            opponentNameResult.setText(session.getName2() + "(" + opponent[0] + ")");
            ownAnimalResult.setText(session.getAnimal1() + "(" + own[1] + ")");
            opponentAnimalResult.setText(session.getAnimal2() + "(" + opponent[1] + ")");
            ownLocationResult.setText(session.getCountry1() + "(" + own[2] + ")");
            opponentLocationResult.setText(session.getCountry2() + "(" + opponent[2] + ")");
            ownObjectResult.setText(session.getThing1() + "(" + own[3] + ")");
            opponentObjectResult.setText(session.getThing2() + "(" + opponent[3] + ")");
        }else{
            int[] own = session.getPoints2();
            int[] opponent = session.getPoints1();
            ownNameResult.setText(session.getName2() + "(" + own[0] + ")");
            opponentNameResult.setText(session.getName1() + "(" + opponent[0] + ")");
            ownAnimalResult.setText(session.getAnimal2() + "(" + own[1] + ")");
            opponentAnimalResult.setText(session.getAnimal1() + "(" + opponent[1] + ")");
            ownLocationResult.setText(session.getCountry2() + "(" + own[2] + ")");
            opponentLocationResult.setText(session.getCountry1() + "(" + opponent[2] + ")");
            ownObjectResult.setText(session.getThing2() + "(" + own[3] + ")");
            opponentObjectResult.setText(session.getThing1() + "(" + opponent[3] + ")");
        }
    }

    public Controller(){
        this.gson = new Gson();
    }


    @Override
    public void onMessage(String line) {
        session = gson.fromJson(line, Session.class);
        if(session.getName1() == null){
            System.out.println(session.getLetter());
            title.setText("Letra: "+session.getLetter());
        } else{
            if(session.getName2() == null){
                if(identifier == null){
                    didnotStop();
                }
            }else{
                showResults();
                showPoints();
            }
        }
    }
}
