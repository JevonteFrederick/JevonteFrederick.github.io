//By: Jevonte Frederick
//5/23/26

package interestCalc;

import java.util.Scanner; 
import java.util.InputMismatchException;

public class InterestCalc {
	
	//Main method
	public static void main(String[] args)
	{
		userEntry(); //Start program by calling userEntry method.
		System.out.println("Exiting program."); //Output statement before exiting program.
	}	
	
	//Method to prompt user input
	public static void userEntry() { 
		UserData data = new UserData(); //Object for UserData class
	
		int year = 0; //Integer for year
		double startAmount = 0.0; //Double starting balance input
		double deposit = 0.0; //Double for user monthly deposit input
		double interest = 0.0; //Double for user interest rate input
	
		char userCharInput = 'y'; //Char to terminate or continue program.
		Scanner userScanner = new Scanner(System.in); //Scanner to read user inputs.
		
		//while loop to prompt user input and generate report
		while (userCharInput == 'y') { 
			
			//Header to prompt for user input
			System.out.println("\nPlease enter values to calculate annual interest earnings.");
			System.out.println("\n************************Data Entry************************"); 
			
			//Read data from user
			startAmount = readInputDouble(userScanner, "Initial Investment Amount: $"); //Read input for startAmount
			data.setStartingAmount(startAmount); //Set starting amount for UserData object from user input
	
			deposit = readInputDouble(userScanner, "Monthly Deposit: $"); //Read input for monthlyDeposits
			data.setMonthlyDeposit(deposit); //Set monthly deposit for UserData object from user input
	
			interest = readInputDouble(userScanner, "Annual Interest: %"); //Read input for interest rate
			data.setInterestRate(interest); //Set interest rate for UserData object from user input
	
			year = readInputInt(userScanner, "Number of Years: "); //Read input for years
			data.setYears(year); //Set years for UserData object from user input
	
			//Generate reports for user
			reportOutput(data);
	
			//Prompt user to continue program
			System.out.println("\nWould you like to enter another report?"); 
			userCharInput = readInputChar(userScanner); //Read operation choice from user
		}
		
		//Exit program loop and close scanner
		userScanner.close();
	}
	
	//Method to output reports from user data
		public static void reportOutput(UserData data) { 
		DataCalculation calculate = new DataCalculation(); //Object for calculating user data

		calculate.printReport(data, 0); //Print report without monthly deposits. 
		calculate.printReport(data, 1); //Print report with monthly deposits.
		}
	
	//Method to read doubles from user
	public static double readInputDouble(Scanner scanning, String prompt) {
		boolean validInput = false; //valid input boolean
		double value = 0; 
		
		//Do while loop to read input from user until valid double is read
		do {
			try {
				//Print specific value prompt from method call
				System.out.print(prompt); 
				value = scanning.nextDouble(); 
				
				//Throw exception if number is negative
				if(value < 0) {
					throw new IllegalArgumentException("Number can't be less than 0.");
				}
				validInput = true;
			}
			//Catch input type exceptions
			catch(InputMismatchException e){
				System.err.println("Please enter a valid number.");
				scanning.nextLine();
			}
			//Catch negative number exceptions
			catch(IllegalArgumentException e) {
				System.err.println(e.getMessage());
				scanning.nextLine();
			}
		}while(!validInput);
		
		//Return value if input is valid
		return value;
	}
	
	//Method to read int from user
	public static int readInputInt(Scanner scanning, String prompt) {
		boolean validInput = false; //Valid input boolean
		int value = 0;
		
		//Do while loop to read input from user until valid int is read
		do {
			try {
				//Print specific value prompt from method call
				System.out.print(prompt); 
				value = scanning.nextInt(); 
				
				//Throw exception if number is negative
				if(value < 0) {
					throw new IllegalArgumentException("Number can't be less than 0.");
				}
				validInput = true;
			}
			//Catch input type exceptions
			catch(InputMismatchException e){
				System.err.println("Please enter a valid number.");
				scanning.nextLine();
			}
			//Catch negative number exceptions
			catch(IllegalArgumentException e) {
				System.err.println(e.getMessage());
				scanning.nextLine();
			}
		}while(!validInput);
		
		//Return value if input is valid.
		return value;
	}
	
	//Method to read char from user
	public static char readInputChar(Scanner scanning) {
		boolean validInput = false; //Valid input boolean
		char value = ' ';
		
		//Do while loop to read char from user until valid char is read
		do {
			try {
				//Prompt user to continue or terminate program.
				System.out.println("Enter 'y' to continue. Enter 'n' to quit: "); 
				value = scanning.next().charAt(0); 
				
				//Throw exception if char is not y or n
				if(value != 'y' && value != 'n') {
					throw new IllegalArgumentException("Invalid character.");
				}
				validInput = true;
			}
			//Catch input type exceptions
			catch(InputMismatchException e){
				System.err.println("Please enter a valid character.");
				scanning.nextLine();
			}
			//Catch char input exceptions
			catch(IllegalArgumentException e) {
				System.err.println(e.getMessage());
				scanning.nextLine();
			}
		}while(!validInput);
		
		//Return value if input is valid.
		return value; 
	}
}
