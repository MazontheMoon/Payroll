import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    //Define variables
    static String employeeID;
    static String firstName;
    static String lastName;
    static int hoursWorked;
    static double payRate;
    static double taxRate;
    static double grossPay;
    static double taxPaid;
    static double netPay;
    static double bonusPay;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Display greeting
        displayGreeting();

        //Get user input
        getEmployeePayrollInfo();

        //Calculate pay information
        calculatePay();

        //Display payslip on screen
        displayPayOnScreen();

        //Create payslip file
        createPayslip();
    }

    /*===============
    DISPLAY GREETING
    =================*/

    public static void displayGreeting() {
        System.out.println("===============================");
        System.out.println("Welcome to the Payroll System");
        System.out.println("===============================");
    }

    /*=============
    GET USER INPUT
    ===============*/

    public static void getEmployeePayrollInfo() {
        getEmployeeID();
        getFirstName();
        getLastName();
        getHoursWorked();
        getPayRate();
        getTaxRate();

        //Closures
        scanner.close();
    }

    public static void getEmployeeID() {
        while (true) {
            try {
                System.out.print("Enter Employee ID: ");
                employeeID = validateEmployeeID(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getFirstName() {
        while (true) {
            try {
                System.out.print("Enter First Name: ");
                firstName = validateName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getLastName() {
        while (true) {
            try {
                System.out.print("Enter Last Name: ");
                lastName = validateName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getHoursWorked() {
        while (true) {
            try {
                System.out.print("Enter Number of Hours Worked: ");
                hoursWorked = validateHours(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getPayRate() {
        while (true) {
            try {
                System.out.print("Enter Rate of Pay: $");
                payRate = validatePay(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getTaxRate() {
        while (true) {
            try {
                System.out.print("Enter Rate of Tax \n (0.1 = 10%, 0.2 = 20%, etc.): ");
                taxRate = validateTax(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    /*=============
    VALIDATE INPUT
    ==============*/

    public static String validateEmployeeID(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be blank.");
        }
        return input;
    }

    public static String validateName(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        return input;
    }

    public static int validateHours(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Hours must be filled in.");
        }

        //Check hours not 0
        int hours = Integer.parseInt(input);
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours worked cannot be 0.");
        }
        return hours;
    }

    public static double validatePay(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Pay rate must be filled in.");
        }

        //Check pay not 0
        double pay = Double.parseDouble(input);
        if (pay <= 0) {
            throw new IllegalArgumentException("Pay rate cannot be 0.");
        }
        return pay;
    }

    public static double validateTax(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Tax rate must be filled in.");
        }

        //Check tax not 0
        double tax = Double.parseDouble(input);
        if (tax <= 0) {
            throw new IllegalArgumentException("Tax rate cannot be 0.");
        }
        return tax;
    }

    /*===================
    PAYROLL CALCULATIONS
    =====================*/

    public static void calculatePay() {
        grossPay = calculateGross(hoursWorked, payRate);
        taxPaid = calculateTax(grossPay, taxRate);
        netPay = calculateNet(grossPay, taxPaid);
        bonusPay = calculateBonus(hoursWorked);
    }

    public static double calculateGross(int hours, double pay) {
        return hours * pay;
    }

    public static double calculateTax(double gross, double tax) {
        return gross * tax;
    }

    public static double calculateNet(double gross, double tax) {
        return gross - tax;
    }

    public static double calculateBonus(int hours) {
        double bonus;

        if (hours >= 50) {
            bonus = 100.00;
        } else if (hours >= 45) {
            bonus = 60.00;
        } else if (hours >= 40) {
            bonus = 50.00;
        } else {
            bonus = 0.0;
        }
        return bonus;
    }

    /*================
    DISPLAY ON SCREEN
    ===================*/

    public static void displayPayOnScreen() {

        System.out.println(formatPayslip());
        System.out.println("=======================================");
        System.out.println("Thank you for using the Payroll System");
        System.out.println("=======================================");

    }

    /*====================
    FORMAT PAYSLIP CONTENT
    ======================*/

    public static String formatPayslip() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(0);

        return String.format(
                """
                        =========================
                        Employee Payslip
                        =========================
                        Employee ID: %s
                        Name: %s %s
                        Hours Worked: %d
                        Hourly Rate: %s
                        Gross Pay: %s
                        Tax Rate: %s
                        Tax Paid: %s
                        Bonus: %s
                        Net Pay: %s
                        =========================
                        """,
                employeeID,
                firstName,
                lastName,
                hoursWorked,
                currency.format(payRate),
                currency.format(grossPay),
                percent.format(taxRate),
                currency.format(taxPaid),
                currency.format(bonusPay),
                currency.format(netPay)
        );
    }
    /*==================
    CREATE PAYSLIP FILE
    ===================*/

    public static void createPayslip() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String filePath = "C:\\temp\\payroll " + employeeID + " " + date.format(dateFormatter) + ".txt";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(formatPayslip());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot locate file.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


}