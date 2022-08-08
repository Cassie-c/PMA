package controller;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Medication;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController extends Application implements Controller, Initializable {
    public TableView<Medication> profileTable;
    public TableColumn<Medication, String> nameCol;
    public TableColumn<Medication, String> barCol;
    public TableColumn<Medication, String> strengthCol;
    public TableColumn<Medication, String> dosageCol;
    public TableColumn<Medication, String> tabletCol;
    public TableColumn<Medication, String> preCol;
    public TableColumn<Medication, String> conflictCol;
    public Stage stage = new Stage();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/profile.fxml")));
        primaryStage.setTitle("Drug Information List");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void showWindow() throws Exception {

        start(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameCol.setCellValueFactory(c -> c.getValue().nameProperty());
        barCol.setCellValueFactory(c -> c.getValue().barcodeProperty());
        strengthCol.setCellValueFactory(c -> c.getValue().strengthProperty());
        dosageCol.setCellValueFactory(c -> new SimpleStringProperty());
        tabletCol.setCellValueFactory(c -> c.getValue().tabletsProperty());
        preCol.setCellValueFactory(c -> c.getValue().pharmaceuticalCompanyProperty());
        conflictCol.setCellValueFactory(c -> new SimpleStringProperty());
        profileTable.setItems(FXCollections.observableList(PMAController.medications));
    }
}
