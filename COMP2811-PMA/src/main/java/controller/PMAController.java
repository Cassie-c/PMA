package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Medication;
import model.WeekMedication;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class PMAController extends Application implements Controller {
    static List<Medication> medications = new CopyOnWriteArrayList<>();
    static Map<String, Medication> medicationBarcodeMap = new ConcurrentHashMap<>();
    static List<WeekMedication> weekMed = new CopyOnWriteArrayList<>();
    public Button ProfileBtn;
    public Button ManEnterBtn;
    public Button BarcodeEnterBtn;
    public Button ResultBtn;
    ManEnterController manEnterController = new ManEnterController();
    BarcodeEnterController barcodeEnterController = new BarcodeEnterController();
    ProfileController profileController = new ProfileController();
    ResultController resultController = new ResultController();
    Stage stage = new Stage();
    public static String BACK_UP_FILENAME = "medications.dat";

    static {
        // load medications
        if (!new File(BACK_UP_FILENAME).exists()) {
            loadDefault();
        } else {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(BACK_UP_FILENAME));
                while (true) {
                    Medication medication = (Medication) objectInputStream.readObject();
                    medications.add(medication);
                }

            } catch (IOException | ClassNotFoundException ignore) {

            }
            if (medications.isEmpty()) {
                loadDefault();
            }
        }
        for (Medication medication : medications) {
            medicationBarcodeMap.put(medication.getBarcode(), medication);
        }
    }

    private static void loadDefault() {
        System.out.println("medications loadDefault ");

        medications.add(new Medication("Bisoprolol fumarate", "5017007025733", 5, 28, "TEVA UK"));
        medications.add(new Medication("Ramipril", "5060124640457", 2.5, 28, "BROWN& BURK"));
        medications.add(new Medication("Dispersible Asprin", "5017007025733", 75, 28, "Aspar"));
        medications.add(new Medication("Atorvastatis", "5017007025733", 80, 28, "TEVA UK"));
        medications.add(new Medication("Eplerenone", "5017007025733", 25, 28, "Synthon BV"));
    }

    public static void addMedication(Medication medication) {
        medications.add(medication);
        medicationBarcodeMap.put(medication.getBarcode(), medication);
    }

    @FXML
    public void profileAct(ActionEvent actionEvent) throws Exception {
        profileController.showWindow();
    }

    @FXML
    public void medRecordAct(ActionEvent actionEvent) throws Exception {
        manEnterController.showWindow();
    }

    @FXML
    public void barcodeRecordAct(ActionEvent actionEvent) throws Exception {
        barcodeEnterController.showWindow();
    }

    @FXML
    public void resultAct(ActionEvent actionEvent) throws Exception {
        resultController.showWindow();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pma.fxml")));
        primaryStage.setTitle("Main interface");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    @Override
    public void showWindow() throws Exception {
        start(stage);
    }

    public static void alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
