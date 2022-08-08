package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Medication;
import model.WeekMedication;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class BarcodeEnterController extends Application implements Controller {
    public TextField barCodeInput;
    public TextField dailyDosage;
    public TextArea resultText;
    Stage stage = new Stage();

    private final List<WeekMedication> weekMedications = new CopyOnWriteArrayList<>();

    public void submitAct(ActionEvent actionEvent) {
        String barcode = barCodeInput.getText();
        double dailyDosage = 0.0;
        Medication medication = PMAController.medicationBarcodeMap.get(barcode);
        if (medication == null) {
            PMAController.alert("No such medication: " + barcode, String.format("No such medication(%s) in allow list", barcode));
            return;
        }
        if (weekMedications.stream().anyMatch(m -> m.getBarcode().equals(barcode))) {
            PMAController.alert(barcode + " already added in list", barcode + " already added in list");
            return;
        }
        String dosageText = this.dailyDosage.getText();
        try {
            dailyDosage = Double.parseDouble(dosageText);
        } catch (Exception e) {
            PMAController.alert("Invalid Dosage", String.format("%s is not a number", dosageText));
            return;
        }
        WeekMedication weekMedication1 = new WeekMedication(medication, dailyDosage);
        weekMedications.add(weekMedication1);
        StringBuilder builder = new StringBuilder();
        for (WeekMedication weekMedication : weekMedications) {
            builder.append(weekMedication.getName()).append("(").append(dailyDosage).append(")").append(" * 7 = ").append(weekMedication1.getWeeklyDosage()).append("\n");
        }
        resultText.setText(builder.toString());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/barcode_enter.fxml")));
        primaryStage.setTitle("Drug Input");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void showWindow() throws Exception {
        PMAController.weekMed.clear();
        start(stage);
    }

    public void confirmAct(ActionEvent actionEvent) throws Exception {
        PMAController.weekMed.addAll(weekMedications);
        weekMedications.clear();
        barCodeInput.setText("");
        resultText.setText("");
        dailyDosage.setText("");
        PMAController.alert("Added successfully", "Added successfully");
    }
}
