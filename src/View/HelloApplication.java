package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application {
    public Scene playerIconScene;
    public Scene gameScene;

    public Scene welcomeScene;

    @Override
    public void start(Stage stage) throws IOException {
        IModel model = new MyModel();
        MyViewModel myViewModel = new MyViewModel(model);
        model.addToMe(myViewModel);

        stage.setTitle("Welcome");
        FXMLLoader welcomeFXML = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        Parent welcome = welcomeFXML.load();
        welcomeScene = new Scene(welcome,900,600);
        welcomeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        WelcomeController welcomeController = welcomeFXML.getController();

        FXMLLoader playerFXML = new FXMLLoader(getClass().getResource("PlayerIcon.fxml"));
        Parent player = playerFXML.load();
        PlayerIconController playerController = playerFXML.getController();
        playerIconScene = new Scene(player,900,600);
        playerController.setStage(stage);


        FXMLLoader gameFXML = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent game = gameFXML.load();
        MyViewController gameController = gameFXML.getController();
        gameScene = new Scene(game,900,600);

        welcomeController.setStage(stage);
        welcomeController.setScene(playerIconScene);

        playerController.setStage(welcomeController.getStage());
        playerController.setScene(gameScene);

        playerController.setMyViewModel(myViewModel);
        //gameController.changeScreenSize(gameScene);
        playerController.setMyViewController(gameController);

        myViewModel.addToMe(gameController);

        stage.setScene(welcomeScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}