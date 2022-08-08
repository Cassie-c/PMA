package model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medication implements Serializable {
    String name;
    String barcode;
    double strength;
    int tablets;
    List<Medication> conflictDrugs;
    String pharmaceuticalCompany;

    public Medication(String name, String barcode, double strength, int tablets, String pharmaceuticalCompany) {
        if (name.length() == 0) {
            throw new RuntimeException("Name is empty");
        }
        if (barcode.length() == 0) {
            throw new RuntimeException("barcode is empty");
        }
        if (strength <= 0) {
            throw new RuntimeException("strength < 0");
        }
        if (tablets <= 0) {
            throw new RuntimeException("tablets <= 0 || tablets>4");
        }
        this.name = name;
        this.barcode = barcode;
        this.strength = strength;
        this.tablets = tablets;
        this.pharmaceuticalCompany = pharmaceuticalCompany;
        conflictDrugs = new ArrayList<>();
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty barcodeProperty() {
        return new SimpleStringProperty(barcode);
    }

    public StringProperty pharmaceuticalCompanyProperty() {
        return new SimpleStringProperty(pharmaceuticalCompany);
    }

    public StringProperty strengthProperty() {
        return new SimpleStringProperty(strength + "");
    }

    public StringProperty tabletsProperty() {
        return new SimpleStringProperty(tablets + "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public int getTablets() {
        return tablets;
    }

    public void setTablets(int tablets) {
        this.tablets = tablets;
    }

    public List<Medication> getConflictDrugs() {
        return conflictDrugs;
    }

    public void setConflictDrugs(List<Medication> conflictDrugs) {
        this.conflictDrugs = conflictDrugs;
    }

    public String getPharmaceuticalCompany() {
        return pharmaceuticalCompany;
    }

    public void setPharmaceuticalCompany(String pharmaceuticalCompany) {
        this.pharmaceuticalCompany = pharmaceuticalCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(barcode, that.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}
