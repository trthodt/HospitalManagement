package Bussiness;

import Model.EofIndicatorClass;
import Model.Patient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a class for managing a collection of patients.
 * Extends HashMap<String, Patient> to store patients by their ID.
 * @author Nguyen Truong Tho
 */
public class PatientMangement extends HashMap<String, Patient> {

    /**
     * Adds a patient to the collection.
     *
     * @param patient The patient object to be added.
     */
    public void addPatient(Patient patient) {
        this.put(patient.getId(), patient);
    }

    /**
     * Retrieves a list of patients within a specified date range.
     *
     * @return A list of patients admitted within the specified date range.
     * @throws ParseException If there is an error parsing the dates.
     */
    public List<Patient> showPatient() throws ParseException {
        List<Patient> showList = new ArrayList<>();
        System.out.println("LIST OF PATIENTS");
        String startDay = Validation.getDate("Enter start day: ");
        String endDay = Validation.getDateAfter("Enter end day: ",startDay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date date1 = sdf.parse(Validation.changeDayPos(startDay));
        Date date2 = sdf.parse(Validation.changeDayPos(endDay));
        List<Patient> patientList = new ArrayList<>(this.values());
        for (Patient p : patientList) {
            Date date3 = sdf.parse(Validation.changeDayPos(p.getAdmissionDate()));
            if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                showList.add(p);
            }
        }
        return showList;
    }

    /**
     * Sorts the list of patients based on the user's choice of sorting criteria
     * and order.
     *
     * @return A sorted list of patients.
     */
    public List<Patient> sortPatient() {
        System.out.println("Sorted by: ");
        System.out.println("1. ID.");
        System.out.println("2. Name.");
        System.out.print("Enter your choice: ");
        int choice = Validation.getInt(1, 2);

        List<Patient> sortList = new ArrayList<>(this.values());
        if (choice == 1) {
            Collections.sort(sortList, (Patient p1, Patient p2) -> p1.getId().compareTo(p2.getId()));
        } else {
            Collections.sort(sortList, (Patient p1, Patient p2) -> p1.getName().compareTo(p2.getName()));
        }
        System.out.println("Sort Order: ");
        System.out.println("1. ASCENDING.");
        System.out.println("2. DESCENDING.");
        System.out.print("Enter your choice: ");
        int order = Validation.getInt(1, 2);
        if (order == 2) {
            Collections.reverse(sortList);
        }
        return sortList;
    }

    /**
     * Displays a list of patients in a formatted table.
     *
     * @param list The list of patients to display.
     */
    public void displayPatient(List<Patient> list) {
        int no = 1;
        System.out.println("____________________________________________________________________________________________");
        System.out.println("| No. | Patient ID |  Admission Date  |      Full Name       |     Phone    |  Diagnosis   |");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Patient l : list) {
            System.out.printf("|%5d", no++);
            System.out.println(l);
        }
        System.out.println("____________________________________________________________________________________________");
        Validation.pressEnterKey();
    }

    /**
     * Saves the patient's list to a file.
     *
     * @param fileName The directory of the file to save the patient's list to.
     */
    public void saveToFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("Patient's list empty!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            List<Patient> list = new ArrayList<>(this.values());
            for (Patient o : list) {
                fo.writeObject(o);
            }
            fo.writeObject(new EofIndicatorClass());
            f.close();
            fo.close();
            System.out.println("Patient's list has been saved!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Loads the patient's list from a file.
     *
     * @param fileName The directory of the file to load the patient's list
     * from.
     */
    public void loadFromFile(String fileName) {
        if (!this.isEmpty()) {
            this.clear();
        }
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                System.out.println("Patient's list empty!");
                return;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInput oj = new ObjectInputStream(fi);
            Object obj;
            while (!((obj = oj.readObject()) instanceof EofIndicatorClass)) {
                Patient patient = (Patient) obj;
                addPatient(patient);
            }
            oj.close();
            fi.close();
            System.out.println("Patient's list has been loaded!");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
