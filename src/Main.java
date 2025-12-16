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
    static double netPay;
    static double bonusPay;

    public static void main(String[] args) {

        //Display greeting
        displayGreeting();

        Scanner scanner = new Scanner(System.in);

        //Get employee ID
        while(true){
            try{
                System.out.print("Enter employee ID: ");
                employeeID = validateEmployeeID(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //Get employee first name
        while(true){
            try{
                System.out.print("Enter first name: ");
                firstName = validateName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //Get employee last name
        while(true){
            try{
                System.out.print("Enter last name: ");
                lastName = validateName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //Get hours worked
        while(true){
            try{
                System.out.print("Enter number of hours worked: ");
                hoursWorked = validateHours(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //Get pay rate
        while(true){
            try{
                System.out.print("Enter rate of pay: $");
                payRate = validatePay(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //Get tax rate
        while(true){
            try{
                System.out.print("Enter rate of tax \n (0.1 = 10%, 0.2 = 20%, etc.): ");
                taxRate = validateTax(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();

        //validate input
        //calculate gross
        //calculate net
        //calculate bonus
        //display payslip on screen
        //write file
        //closures
    }

    public static void displayGreeting(){
        System.out.println("===============================");
        System.out.println("Welcome to the Payroll System");
        System.out.println("===============================");
    }

    /*===================
    VALIDATE INPUT FIELDS
    =====================*/

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

}