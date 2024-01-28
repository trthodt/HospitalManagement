package Bussiness;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Provides methods for validating user input.
 *
 * @author Nguyen Truong Tho
 */
public class Validation {

    private static final Scanner input = new Scanner(System.in);
    private static final String dateFormat = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)[0-9]{2,2}$";

    /**
     * Method for inputting an integer number within a specified range.
     *
     * @param min The minimum value of the number.
     * @param max The maximum value of the number.
     * @return An integer number within the range entered by the user.
     */
    public static int getInt(int min, int max) {
        int n;
        while (true) {
            try {
                n = Integer.parseInt(input.nextLine());
                if (n >= min && n <= max) {
                    return n;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                System.out.print("Number must be in range (" + min + "-" + max + "): ");
            }
        }
    }

    /**
     * Gets an integer value from the user within a specified range.
     *
     * @param msg The message prompt to display.
     * @param exmsg The exception message to display when the value is outside
     * the range.
     * @param min The minimum allowed value (inclusive).
     * @param max The maximum allowed value (inclusive).
     * @return The integer value entered by the user.
     * @throws Exception If the value is outside the specified range or is not a
     * valid integer.
     */
    public static int getInt(String msg, String exmsg, int min, int max) throws Exception {
        int n;
        while (true) {
            try {
                System.out.print(msg);
                n = Integer.parseInt(input.nextLine());
                if (n >= min && n <= max) {
                    return n;
                }
                if (n < 0) {
                    throw new Exception(exmsg);
                }
                throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                System.out.println("Wrong format! ");
            } catch (Exception ex) {
                System.out.println("Wrong format! " + ex.getMessage());
            }

        }
    }

    /**
     * Method for inputting a double number within a specified range.
     *
     * @param min The minimum value of the number.
     * @param max The maximum value of the number.
     * @return A double number within the range entered by the user.
     */
    public static double getDouble(double min, double max) {
        double n;
        while (true) {
            try {
                n = input.nextDouble();
                if (n >= min && n <= max) {
                    return n;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                System.out.print("Number must be in range (" + min + "-" + max + "): ");
            }
        }

    }

    /**
     * Method for inputting a double number within a specified range.
     *
     * @param msg The message to display when prompting for input.
     * @param exmsg The exception message to display when the input is invalid.
     * @param min The minimum value of the number.
     * @param max The maximum value of the number.
     * @return A double number within the range entered by the user.
     * @throws Exception if the input is not a valid double or falls outside the
     * specified range.
     */
    public static double getDouble(String msg, String exmsg, double min, double max) throws Exception {
        double n;
        while (true) {
            try {
                System.out.print(msg);
                n = Double.parseDouble(input.nextLine());
                if (n >= min && n <= max) {
                    return n;
                }
                if (n < 0) {
                    throw new Exception(exmsg);
                }
                throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                System.out.println("Wrong format! ");
            } catch (Exception ex) {
                System.out.println("Wrong format! " + ex.getMessage());
            }

        }
    }

    /**
     * Method for inputting a Yes or No choice.
     *
     * @return The user's input, either "Y" or "N".
     */
    public static String getYesNo() {
        String n;
        while (true) {
            n = input.nextLine();
            if (n.equalsIgnoreCase("y") || n.equalsIgnoreCase("n")) {
                return n;
            } else {
                System.out.println("Yes or No? (Y/N)");
            }
        }

    }

    /**
     * Method for inputting a string that matches a specified format using
     * regular expression.
     *
     * @param msg The message prompt for the input.
     * @param format The desired format of the string.
     * @param regex The regular expression pattern to match the string format.
     * @return The user's input string.
     */
    public static String getString(String msg, String format, String regex) {
        while (true) {
            try {
                System.out.print(msg);
                String str = input.nextLine();
                Pattern pt = Pattern.compile(regex);
                if (pt.matcher(str).find()) {
                    return str;
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("String must be in " + format + " format!");
            }
        }
    }

    /**
     * Method for inputting a string.
     *
     * @param msg The message prompt for the input.
     * @return The user's input string.
     */
    public static String getString(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }

    /**
     * Method for inputting the gender (Male or Female).
     *
     * @return The user's input for gender.
     */
    public static String getGender() {
        while (true) {
            try {
                System.out.print("Enter gender (Male or Female): ");
                String gender = input.nextLine();
                if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                    return gender;
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("Gender must be Male or Female!");
            }
        }
    }

    /**
     * Method for inputting a phone number in the specified format.
     *
     * @param msg The message to display when prompting for input.
     * @return The user's input for the phone number.
     */
    public static String getPhone(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String phone = input.nextLine();
                Pattern pt = Pattern.compile("^0[1-9]{1,1}[0-9]{8,8}$");
                if (pt.matcher(phone).find()) {
                    return phone;
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("Number must be in phone number format!");
            }
        }
    }

    /**
     * Method for inputting a shift (Day or Night).
     *
     * @return The user's input for the shift.
     */
    public static String getShift() {
        while (true) {
            try {
                System.out.print("Enter shift (Day or Night): ");
                String shift = input.nextLine();
                if (shift.equalsIgnoreCase("day") || shift.equalsIgnoreCase("night")) {
                    return shift;
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("Shift must be Day or Night!");
            }
        }
    }

    /**
     * Method for inputting a date in the specified format.
     *
     * @param msg The message to display for input.
     * @return The user's input for the date.
     */
    public static String getDate(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String date = input.nextLine();
                Pattern pt = Pattern.compile(dateFormat);
                if (pt.matcher(date).find() && isValidDate(date)) {
                    return date;
                }
                throw new Exception("the date must be in dd/mm/yyyy format!");
            } catch (Exception ex) {
                System.out.println("Wrong date format, " + ex.getMessage());
            }
        }
    }
    public static String getDateAfter(String msg, String datebefore) {
        while (true) {
            try {
                System.out.print(msg);
                String date = input.nextLine();
                Pattern pt = Pattern.compile(dateFormat);
                if (pt.matcher(date).find() && isValidDate(date)) {
                    if (!isDateAfter(formatDate(datebefore),formatDate(date))){
                        throw new Exception("your input date must be after " + datebefore + "!");
                    }
                    return date;
                }
                throw new Exception("the date must be in dd/mm/yyyy format!");
            } catch (Exception ex) {
                System.out.println("Wrong date format, " + ex.getMessage());
            }
        }
    }

    /**
     * Checks if a given date is valid.
     *
     * @param date The date to be validated.
     * @return True if the date is valid, false otherwise.
     */
    public static boolean isValidDate(String date) {
        String[] split = date.split("[-/. ]");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        int maxDay = 30;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            maxDay = 31;
        }
        if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        }
        return day <= maxDay;
    }

    /**
     * Changes the position of the day in a date string.
     *
     * @param date The date string to be modified.
     * @return The modified date string with the day and month positions
     * swapped.
     */
    public static String changeDayPos(String date) {
        String[] split = date.split("[-/. ]");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        return month + "/" + day + "/" + year;
    }

    public static String formatDate(String date) {
        String[] split = date.split("[-/. ]");
        String day = split[0];
        String month = split[1];
        String year = split[2];
        return year + month + day;
    }
    private static boolean isDateAfter(String date1, String date2){
        return date1.compareTo(date2)<=0;
    }

    /**
     * Waits for the user to press the "ENTER" key to continue.
     */
    public static void pressEnterKey() {
        System.out.print("Press \"ENTER\" to continue...");
        input.nextLine();
    }
}
