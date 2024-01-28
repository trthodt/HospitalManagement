package Bussiness;

import Model.Nurse;
import Model.Patient;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * The HospitalManagement class handles operations related to managing nurses
 * and patients in a hospital.
 *
 * @author Nguyen Truong Tho
 */
public class HospitalManagement {

    private int dataChange = 0;
    private final String nurseFile = "src\\file\\nurses.dat";
    private final String patientFile = "src\\file\\patients.dat";
    Scanner input = new Scanner(System.in);
    NurseManagement nurseList = new NurseManagement();
    PatientMangement patientList = new PatientMangement();

    //========================Nurse Management==================================
    /**
     * Adds a new nurse to the nurse list.
     *
     * @throws Exception if an error occurs during the nurse addition process.
     */
    public void addNurse() throws Exception {
        while (true) {
            String staffID = Validation.getString("Enter Staff ID (NXXX): ", "NXXX (with X is digit)", "^[Nn]{1,}[0-9]{3,3}$").toUpperCase();
            if (!nurseList.containsKey(staffID)) {
                String name = Validation.getString("Enter Name: ", "name", "^[a-zA-Z ]+$");
                int age = Validation.getInt("Enter age: ", "Age must be greater than 0!", 1, Integer.MAX_VALUE);
                String gender = Validation.getGender();
                String address = Validation.getString("Enter address: ");
                String phone = Validation.getPhone("Enter phone number: ");
                String department = Validation.getString("Enter department: ", "department", "^[a-zA-Z0-9 ]{3,50}$");
                String shift = Validation.getShift();
                double salary = Validation.getDouble("Enter salary: ", "Salary must be greater than 0!", 0, Double.MAX_VALUE);
                Nurse nurse = new Nurse(staffID, name, age, gender, address, phone, department, shift, salary);
                nurseList.addNurse(nurse);
                System.out.println("Nurse " + name + " has been added!");
                setDataChange(getDataChange() + 1);
            } else {
                System.out.println("The nurse already exist!");
            }
            System.out.println("Continue adding a new nurse? (Y/N)");
            String choice = Validation.getYesNo();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

    }

    /**
     * Finds a nurse by name and displays the matching results.
     */
    public void findNurse() {
        String name = Validation.getString("Enter Name: ", "name", "^[a-zA-Z ]+$");
        List<Nurse> list = nurseList.findNurse(name);
        try {
            if (!list.isEmpty()) {
                nurseList.displayNurse(list);
                return;
            }
            throw new Exception();
        } catch (Exception ex) {
            System.out.println("The nurse does not exist!");
        }
    }

    /**
     * Updates the details of a nurse based on the provided staff ID.
     */
    public void updateNurse() {
        String staffID = Validation.getString("Enter Staff ID (NXXX): ", "NXXX (with X is digit)", "^[Nn]{1,}[0-9]{3,3}$").toUpperCase();
        nurseList.updateNurse(staffID);
        setDataChange(getDataChange() + 1);
    }

    /**
     * Deletes a nurse from the nurse list based on the provided staff ID.
     */
    public void deleteNurse() {
        String staffID = Validation.getString("Enter Staff ID (NXXX): ", "NXXX (with X is digit)", "^[Nn]{1,}[0-9]{3,3}$").toUpperCase();
        if (nurseList.containsKey(staffID)) {
            if (getNurseTask(staffID) > 0) {
                System.out.println("Cannot be deleted because this nurse has a task!");
            } else {
                System.out.println("Do you want to delete " + nurseList.get(staffID).getName() + "? (Y/N)");
                String n = Validation.getYesNo();
                if (n.equalsIgnoreCase("y")) {
                    nurseList.deleteNurse(staffID);
                    System.out.println("Success!");
                    setDataChange(getDataChange() + 1);
                } else {
                    System.out.println("Failure!");
                }
            }
        } else {
            System.out.println("The nurse does not exist!");
        }

    }

    /**
     * Retrieves the number of tasks assigned to a nurse based on the staff ID.
     *
     * @param staffId the staff ID of the nurse.
     * @return the number of tasks assigned to the nurse.
     */
    public int getNurseTask(String staffId) {
        List<Patient> pList = new ArrayList<>(patientList.values());
        int count = 0;
        for (Patient p : pList) {
            if (p.getNurseAssigned().contains(staffId)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the number of available nurses (those with fewer than two
     * tasks).
     *
     * @return the number of available nurses.
     */
    public int getNurseAvailable() {
        List<Nurse> nList = new ArrayList<>(nurseList.values());
        int count = 0;
        for (Nurse n : nList) {
            if (getNurseTask(n.getStaffID()) < 2) {
                count++;
            }
        }
        return count;
    }

    //========================Patient Management================================
    /**
     * Adds a new patient to the patient list, ensuring that there are at least
     * two available nurses.
     *
     * @throws Exception if all nurses are busy and cannot accommodate more
     * patients.
     */
    public void addPatient() throws Exception {
        if (getNurseAvailable() < 2) {
            System.out.println("No available nurse, cannot add more patient!");
            return;
        }
        while (true) {
            String id = Validation.getString("Enter ID (PXXX): ", "PXXX (with X is digit)", "^[pP]{1,}[0-9]{3,}$").toUpperCase();
            if (!patientList.containsKey(id)) {
                String name = Validation.getString("Enter Name: ", "name", "^[a-zA-Z ]+$");
                int age = Validation.getInt("Enter age: ", "Age must be greater than 0!", 1, Integer.MAX_VALUE);
                String gender = Validation.getGender();
                String address = Validation.getString("Enter address: ");
                String phone = Validation.getPhone("Enter Phone Number: ");
                String diagnosis = Validation.getString("Enter diagnosis: ");
                String admissionDate = Validation.getDate("Enter admission Date: ");
                String dischargeDate = Validation.getDateAfter("Enter dischargeDate: ",admissionDate);
                TreeSet nurseAssigned = getNurseAssign();
                Patient p = new Patient(id, name, age, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned);
                patientList.addPatient(p);
                System.out.println("Patient " + name + " has been added!");
                setDataChange(getDataChange() + 1);
            } else {
                System.out.println("The patient already exist!");
            }
            if (getNurseAvailable() < 2) {
                System.out.println("No available nurse, cannot add more patient!");
                return;
            }
            System.out.println("Continue adding a new patient? (Y/N)");
            String choice = Validation.getYesNo();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

    }

    /**
     * Retrieves a set of nurse assignments for a patient.
     *
     * @return a TreeSet containing the staff IDs of assigned nurses.
     */
    public TreeSet getNurseAssign() {
        TreeSet nurseAssign = new TreeSet();
        while (nurseAssign.size() < 2) {
            String staffId = Validation.getString("Enter staff ID (NXXX): ", "NXXX (with X is digit)", "^[Nn]{1,}[0-9]{3,3}$").toUpperCase();
            if (nurseList.containsKey(staffId)) {
                if (getNurseTask(staffId) < 2) {
                    if (!nurseAssign.contains(staffId)) {
                        nurseAssign.add(staffId);
                    } else {
                        System.out.println("This nurse already add to this Patient!");
                    }
                } else {
                    System.out.println("This nurse already has two Patient!");
                }
            } else {
                System.out.println("This nurse does not exist!");
            }
        }
        return nurseAssign;
    }

    /**
     * Retrieves and displays a list of patients within a specified date range.
     *
     * @throws ParseException if an error occurs during date parsing.
     */
    public void showPatient() throws ParseException {
        List<Patient> list = patientList.showPatient();
        if (!list.isEmpty()) {
            patientList.displayPatient(list);
        } else {
            System.out.println("Patient's list empty!");
        }
    }

    /**
     * Sorts the patient list based on user-defined criteria (ID or name) and
     * order (ascending or descending).
     */
    public void sortPatient() {
        List<Patient> list = patientList.sortPatient();
        if (!list.isEmpty()) {
            patientList.displayPatient(list);
        } else {
            System.out.println("Patient's list empty!");
        }

    }

    //==========================Hospital Management=============================
    /**
     * Saves the nurse and patient lists to separate files.
     */
    public void saveData() {
        nurseList.saveToFile(nurseFile);
        patientList.saveToFile(patientFile);
        setDataChange(0);
    }

    /**
     * Loads the nurse and patient lists from the saved files.
     */
    public void loadData() {
        nurseList.loadFromFile(nurseFile);
        patientList.loadFromFile(patientFile);
    }

    /**
     * Prompts the user to save the data if changes were made and then
     * terminates the program.
     */
    public void quitProgam() {
        System.out.println("Do you want to quit progam? (Y/N)");
        String choice = Validation.getYesNo();
        if (choice.equalsIgnoreCase("y")) {
            if (dataChange > 0) {
                System.out.println("Data has been changed! Do you want to save before quit? (Y/N)");
                String savechoice = Validation.getYesNo();
                if (savechoice.equalsIgnoreCase("y")) {
                    saveData();
                }
            }
            System.out.println("Goodbye, have a nice day!");
            System.exit(0);
        }
        System.out.println("Continue program!");
    }

    public int getDataChange() {
        return dataChange;
    }

    public void setDataChange(int dataChange) {
        this.dataChange = dataChange;
    }

}
