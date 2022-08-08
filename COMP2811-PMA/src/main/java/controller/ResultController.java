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
import model.WeekMedication;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController extends Application implements Controller, Initializable {

    public TableView<WeekMedication> resultTable;

    public TableColumn<WeekMedication, String> nameCol;
    public TableColumn<WeekMedication, String> barcodeCol;
    public TableColumn<WeekMedication, String> strengthCol;
    public TableColumn<WeekMedication, String> dosageCol;
    public TableColumn<WeekMedication, String> preCol;
    public TableColumn<WeekMedication, String> conflictDrugsCol;
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/result.fxml")));
        primaryStage.setTitle("Patient Prescription List");
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
        barcodeCol.setCellValueFactory(c -> c.getValue().barcodeProperty());
        strengthCol.setCellValueFactory(c -> c.getValue().strengthProperty());
        dosageCol.setCellValueFactory(c -> c.getValue().weekDosageProperty());
        preCol.setCellValueFactory(c -> c.getValue().pharmaceuticalCompanyProperty());
        conflictDrugsCol.setCellValueFactory(c -> new SimpleStringProperty());
        resultTable.setItems(FXCollections.observableList(PMAController.weekMed));
    }
}
