package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WeekMedication extends Medication {
    private final double dailyDosage;

    public WeekMedication(Medication medication, double dailyDosage) {
        super(medication.name, medication.barcode, medication.strength, medication.tablets, medication.pharmaceuticalCompany);
        this.dailyDosage = dailyDosage;
    }

    public double getWeeklyDosage() {
        return dailyDosage * 7;
    }


    public StringProperty weekDosageProperty() {
        return new SimpleStringProperty(dailyDosage + "");
    }


}
