import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static void displayGreeting(){
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
        while(true){
            try{
                System.out.print("Enter employee ID: ");
                employeeID = validateEmployeeID(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getFirstName() {
        while(true){
            try{
                System.out.print("Enter first name: ");
                firstName = validateName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getLastName() {
        while(true){
            try{
                System.out.print("Enter last name: ");
                lastName = validateName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getHoursWorked(){
        while(true){
            try{
                System.out.print("Enter number of hours worked: ");
                hoursWorked = validateHours(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getPayRate() {
        while(true){
            try{
                System.out.print("Enter rate of pay: $");
                payRate = validatePay(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void getTaxRate(){
        while(true){
            try{
                System.out.print("Enter rate of tax \n (0.1 = 10%, 0.2 = 20%, etc.): ");
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

   public static String validateEmployeeID(String input){
        //Check for blank input
        if(input.isEmpty()){
            throw new IllegalArgumentException("Employee ID cannot be blank.");
        }
        return input;
   }

   public static String validateName(String input){
       //Check for blank input
       if(input.isEmpty()){
           throw new IllegalArgumentException("Name cannot be blank.");
       }
       return input;
   }

    public static int validateHours(String input){
        //Check for blank input
        if(input.isEmpty()){
            throw new IllegalArgumentException("Hours must be filled in.");
        }

        //Check hours not 0
        int hours = Integer.parseInt(input);
        if(hours <=0){
            throw new IllegalArgumentException("Hours worked cannot be 0.");
        }
        return hours;
    }

    public static double validatePay(String input){
        //Check for blank input
        if(input.isEmpty()){
            throw new IllegalArgumentException("Pay rate must be filled in.");
        }

        //Check pay not 0
        double pay = Double.parseDouble(input);
        if(pay <=0){
            throw new IllegalArgumentException("Enter valid pay rate.");
        }
        return pay;
    }

    public static double validateTax(String input){
        //Check for blank input
        if(input.isEmpty()){
            throw new IllegalArgumentException("Tax rate must be filled in.");
        }

        //Check tax not 0
        double tax = Double.parseDouble(input);
        if(tax <=0){
            throw new IllegalArgumentException("Tax rate cannot be 0.");
        }
        return tax;
    }

    /*===================
    PAYROLL CALCULATIONS
    =====================*/

    public static void calculatePay(){
        grossPay = calculateGross(hoursWorked, payRate);
        taxPaid = calculateTax(grossPay, taxRate);
        netPay = calculateNet(grossPay, taxPaid);
        bonusPay = calculateBonus(hoursWorked);
    }

    public static double calculateGross(int hours, double pay){
       return hours * pay;
    }

    public static double calculateTax(double gross, double tax){
        return gross * tax;
    }

    public static double calculateNet(double gross, double tax){
        return gross - tax;
    }

    public static double calculateBonus(int hours){
       double bonus;

        if(hours >= 50)
        {
            bonus = 100.00;
        }
        else if(hours >= 45){
            bonus = 80.00;
        }
        else if(hours >= 40)
        {
            bonus = 60.00;
        }
        else
        {
            bonus = 0.0;
        }
        return bonus;
    }

    /*================
    DISPLAY ON SCREEN
    ===================*/

    public static void displayPayOnScreen(){
       System.out.println("===============================");
       System.out.println("Employee Payslip");
       System.out.println("===============================");
       System.out.println("Employee ID: " + employeeID);
       System.out.println("Name:  " + getFullName(firstName, lastName));
       System.out.println("Hours Worked:  " + hoursWorked);
       System.out.println("Hourly Rate: $" + payRate);
       System.out.println("Gross Pay: $" + grossPay);
       System.out.println("Tax Rate: " + getTaxPercentage(taxRate));
       System.out.println("Tax Paid:: $" + grossPay);
       System.out.println("Bonus: $" + bonusPay);
       System.out.println("Net Pay: $" + netPay);
       System.out.println("===============================");
   }

    public static String getFullName(String fName, String lName){
       return fName + " " + lName;
    }

    public static String getTaxPercentage(double rate){
        return String.format("%.0f%%", rate * 100);
    }

    /*==================
    CREATE PAYSLIP FILE
    ===================*/

    public static void createPayslip(){
        String filePath = "C:\\temp\\payroll.txt";

        try(FileWriter writer = new FileWriter(filePath)){
            writer.write("test");
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot locate file.");
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }


    }


}