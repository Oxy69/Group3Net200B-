package bb.bloodbank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("mnpage.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Welcome Page!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}