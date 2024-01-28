package Model;

import java.io.Serializable;
import java.util.TreeSet;

/**
 *
 * @author Nguyen Truong Tho
 */
public class Patient extends Person implements Serializable {
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private TreeSet nurseAssigned;

    //id, name, age, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned
    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis, String admissionDate, String dischargeDate, TreeSet nurseAssigned) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public TreeSet getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(TreeSet nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %16s | %-20s | %12s | %12s |", getId(), getAdmissionDate(), getName(), getPhone(), getDiagnosis());
    }
}
