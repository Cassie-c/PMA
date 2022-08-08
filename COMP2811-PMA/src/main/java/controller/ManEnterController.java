package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Medication;

import java.util.Objects;

public class ManEnterController extends Application implements Controller {
    public TextField name;
    public TextField strength;
    public TextField dailyDosage;
    public TextField tablets;
    public TextField pharmaceuticalCompany;
    public TextField barcode;
    public TextField conflictDrugs;
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/man_enter.fxml")));
        primaryStage.setTitle("Drug Input");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    @Override
    public void showWindow() throws Exception {
        start(stage);
    }

    @FXML
    public void submitAct(ActionEvent actionEvent) {
        Medication medication;
        try {
            medication = new Medication(name.getText(), barcode.getText(),
                    Double.parseDouble(strength.getText()),
                    Integer.parseInt(tablets.getText()), pharmaceuticalCompany.getText());
        } catch (Exception e) {
            e.printStackTrace();
            PMAController.alert("Incorrect medication information", "Please check medication information: " + e.getMessage());
            return;
        }
        String barcode = medication.getBarcode();
        if (PMAController.medicationBarcodeMap.containsKey(barcode)) {
            PMAController.alert("Addition of pre-existing medication is not allowed", "Medicine " + barcode + " existed");
            return;
        }

        PMAController.alert("Add successful", "Successfully added to drug library list");
        PMAController.addMedication(medication);
    }
}
