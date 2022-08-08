package controller;


import model.Medication;
import model.WeekMedication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BarcodeEnterControllerTest {
    @Test
    public void testWeeklyDosageForUserEnterDailyDosage() {
        Medication medication = new Medication("1", "2", 3d, 4, "5");
        WeekMedication weekMedication = new WeekMedication(medication, 5);
        Assertions.assertEquals(35d, weekMedication.getWeeklyDosage());
        System.out.println("aaa");
    }

}