package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Medication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pma.fxml")));
        primaryStage.setTitle("Drug information entry");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(e -> {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PMAController.BACK_UP_FILENAME));
                for (Medication medication : PMAController.medications) {
                    objectOutputStream.writeObject(medication);
                }
                objectOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        primaryStage.show();
    }

}
