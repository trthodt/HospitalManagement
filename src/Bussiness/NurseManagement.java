package Bussiness;

import Model.EofIndicatorClass;
import Model.Nurse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a class for managing a collection of nurses.
 * Extends HashMap<String, Nurse> to store nurses by their staff ID.
 * @author Nguyen Truong Tho
 */
public class NurseManagement extends HashMap<String, Nurse> {

    /**
     * Adds a nurse to the collection.
     *
     * @param nurse The nurse object to be added.
     */
    public void addNurse(Nurse nurse) {
        this.put(nurse.getStaffID(), nurse);
    }

    /**
     * Finds nurses whose name contains the specified search term.
     *
     * @param name The search term to match against nurse names.
     * @return A list of nurses whose name contains the search term.
     */
    public List<Nurse> findNurse(String name) {
        List<Nurse> list = new ArrayList<>();
        for (Nurse o : this.values()) {
            if (o.getName().toLowerCase().contains(name.toLowerCase())) {
                list.add(o);
            }
        }
        return list;
    }

    /**
     * Updates the information of a nurse identified by the staff ID.
     *
     * @param staffID The staff ID of the nurse to update.
     */
    public void updateNurse(String staffID) {
        if (this.containsKey(staffID)) {
            System.out.println("Type information want to update (press enter to skip field).");
            String name = Validation.getString("Name: ");
            String age = Validation.getString("Age: ");
            String gender = Validation.getString("Gender: ");
            String address = Validation.getString("Address: ");
            String phone = Validation.getString("Phone: ");
            String department = Validation.getString("Department: ");
            String shift = Validation.getString("Shift: ");
            String salary = Validation.getString("Salary: ");
            try {
                if (!name.isEmpty() && !name.matches("^[a-zA-Z ]+$")) {
                    throw new Exception("Wrong name format!");
                }
                if (!age.isEmpty() && Integer.parseInt(age) < 0) {
                    throw new Exception("Age must be greater than 0!");
                }
                if (!gender.isEmpty() && (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"))) {
                    throw new Exception("Gender must be male or female!");
                }
                if (!phone.isEmpty() && !phone.matches("^0[1-9]{1,1}[0-9]{8,8}$")) {
                    throw new Exception("Wrong phone number format!");
                }
                if (!department.isEmpty() && !department.matches("^[a-zA-Z0-9 ]{3,50}")) {
                    throw new Exception("Wrong department format!");
                }
                if (!shift.isEmpty() && !shift.equalsIgnoreCase("day") && !shift.equalsIgnoreCase("night")) {
                    throw new Exception("Shift must be day or night!");
                }
                if (!salary.isEmpty() && Double.parseDouble(salary) < 0) {
                    throw new Exception("Salary must be greater than 0!");
                }
            } catch (Exception ex) {
                System.out.println("Update failed! " + ex.getMessage());

            } finally {
                if (!name.isEmpty()) {
                    this.get(staffID).setName(name);
                }
                if (!age.isEmpty()) {
                    this.get(staffID).setAge(Integer.parseInt(age));
                }
                if (!gender.isEmpty()) {
                    this.get(staffID).setGender(gender);
                }
                if (!address.isEmpty()) {
                    this.get(staffID).setAddress(address);
                }
                if (!phone.isEmpty()) {
                    this.get(staffID).setPhone(phone);
                }
                if (!department.isEmpty()) {
                    this.get(staffID).setDepartment(department);
                }
                if (!shift.isEmpty()) {
                    this.get(staffID).setShift(shift);
                }
                if (!salary.isEmpty()) {
                    this.get(staffID).setSalary(Double.parseDouble(salary));
                }
                System.out.println("Success!");
            }

        } else {
            System.out.println("The nurse does not exist!");
        }
    }

    /**
     * Deletes a nurse from the collection based on the staff ID.
     *
     * @param staffID The staff ID of the nurse to be deleted.
     */
    public void deleteNurse(String staffID) {
        this.remove(staffID);
    }

    /**
     * Displays the information of nurses in a formatted table.
     *
     * @param list The list of nurses to be displayed.
     */
    public void displayNurse(List<Nurse> list) {
        int no = 1;
        System.out.println("_________________________________________________________________________");
        System.out.println("| No. |  Nurse ID  |     Nurse's Name     |     Phone    |    Address   |");
        System.out.println("-------------------------------------------------------------------------");
        for (Nurse l : list) {
            System.out.printf("|%5d", no++);
            System.out.println(l);
        }
        System.out.println("_________________________________________________________________________");
        Validation.pressEnterKey();
    }

    /**
     * Saves the nurse's list to a file.
     *
     * @param fileName The directory of the file to save the nurse's list to.
     */
    public void saveToFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("Nurse's list empty!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            List<Nurse> list = new ArrayList<>(this.values());
            for (Nurse o : list) {
                fo.writeObject(o);
            }
            fo.writeObject(new EofIndicatorClass());
            f.close();
            fo.close();
            System.out.println("Nurse's list has been saved!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Loads the nurse's list from a file.
     *
     * @param fileName The directory of the file to load the nurse's list from.
     */
    public void loadFromFile(String fileName) {
        if (!this.isEmpty()) {
            this.clear();
        }
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                System.out.println("Nurse's list empty!");
                return;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream oj = new ObjectInputStream(fi);
            Object obj;
            while (!((obj = oj.readObject()) instanceof EofIndicatorClass)) {
                Nurse nurse = (Nurse) obj;
                this.put(nurse.getStaffID(), nurse);
            }
            oj.close();
            fi.close();
            System.out.println("Nurse's list has been loaded!");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }

}
