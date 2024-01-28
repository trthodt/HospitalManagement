package Control;

/**
 *
 * @author Nguyen Truong Tho
 */
import Bussiness.Validation;

public class Menu {
    /**
     * Displays the menu options and prompts the user to enter a choice.
     * 
     * @param options An array of options to display in the menu.
     * @return The selected choice as an integer.
     */
    public static int getChoice(Object[] options) {
        System.out.println("==================MENU==================");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("| %-36s |\n",options[i]);
        }
        System.out.println("========================================");
        System.out.print("Your options from 1 - " + options.length + ": ");
        return Validation.getInt(1, options.length);
    }
}