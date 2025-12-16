import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Define variables
        String employeeID;
        String firstName;
        String lastName;
        int hoursWorked;
        double payRate;
        double taxRate;
        double grossPay;
        double netPay;
        double bonusPay;

        //Display greeting
        displayGreeting();

        //Get input
        getPayrollInput();
        //validate input
        //calculations
        //display payslip on screen
        //write file
        //closures
    }

    public static void displayGreeting(){
        System.out.println("===============================");
        System.out.println("Welcome to the Payroll System");
        System.out.println("===============================");
    }

    public static void getPayrollInput(){

        Scanner scanner = new Scanner(System.in);


    }

}