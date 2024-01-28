package Control;

import Bussiness.HospitalManagement;
import java.text.ParseException;

/**
 *
 * @author Nguyen Truong Tho
 */
public class Main {
    public static void main(String[] args) throws ParseException, Exception {
        String[] options = {"1. Create a nurse.","2. Find a nurse.","3. Update a nurse.",
            "4. Delete a nurse.","5. Add a patient.","6. Display a patient.","7. Sort patients.",
            "8. Save data.","9. Load data.","10. Quit."};
        int choice = 0;
        HospitalManagement hospital = new HospitalManagement();
        do {
            choice = Menu.getChoice(options);
            switch (choice){
                case 1://Create a nurse 
                    hospital.addNurse();
                    break;
                case 2://Find a nurse
                    hospital.findNurse();
                    break;
                case 3://Update a nurse
                    hospital.updateNurse();
                    break;
                case 4://Delete a nurse
                    hospital.deleteNurse();
                    break;
                case 5://Add a patient
                    hospital.addPatient();
                    break;
                case 6://Display patients
                    hospital.showPatient();
                    break;
                case 7://Sort patients
                    hospital.sortPatient();
                    break;
                case 8://save data
                    hospital.saveData();
                    break;
                case 9://Load data
                    hospital.loadData();
                    break;
                case 10://Quit
                    hospital.quitProgam();
                    break;
            }
        } while (choice>0&&choice<options.length+1);
    }
}
