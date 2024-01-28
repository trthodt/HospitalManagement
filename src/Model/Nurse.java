package Model;

import java.io.Serializable;

/**
 *
 * @author Nguyen Truong Tho
 */
public class Nurse extends Person implements Serializable {

    private String staffID;
    private String department;
    private String shift;
    private double salary;

    public Nurse(String staffID, String name, int age, String gender, String address, String phone, String department, String shift, double salary) {
        super(name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %12S | %12s | ", getStaffID(), getName(), getPhone(), getAddress());
    }
}
