package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        primaryStage.setTitle("Sticky notes");
        primaryStage.setScene(new Scene(root, 1500, 900));
        primaryStage.show();
        Main.primaryStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
