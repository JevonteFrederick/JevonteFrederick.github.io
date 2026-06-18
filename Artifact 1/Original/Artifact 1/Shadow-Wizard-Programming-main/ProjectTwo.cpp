/*
 * ProjectTwo.cpp
 * Date: 4/5/2025
 * By: Jevonte Frederick
 */

#include "UserData.h" //Include UserData file
#include "DataCalculation.h" //Include DataCalculation file

#include <iostream>
#include <iomanip>
using namespace std;

void reportOutput(UserData data) { //Output reports from user data
	DataCalculation calculate; //Class object for calculating user data

	calculate.printReport(data); //Print report without monthly deposits
	calculate.printMonthlyDepositsReport(data); //Print report with monthly deposits
}

void userEntry() { //Function to prompt user input
	UserData data; //Class object for UserData

	int year; //variable for user year input
	double startAmount; //variable for user starting balance input
	double deposit; //variable for user monthly deposit input
	double interest; //variable for user interest rate input

	char userInput = 'y'; //char for user choice to generate more reports

	while (userInput == 'y') { //while loop for user input and report output
		bool validInput = false; //variable to check valid inputs from user

		cout << "\nPlease enter values to calculate annual interest earnings.";
		cout << "\n************************Data Entry************************" << endl; //Header for user input

		while (!validInput) { //Loop while user input is not valid input
			cout << "Initial Investment Amount: $"; //Prompt user for starting amount
			cin >> startAmount; //Read starting amount from user
			if (!(startAmount > 0) || cin.fail()) { //Prompt user to enter input again if invalid
				cin.clear(); //Clear input to prompt user for again
				cin.ignore(numeric_limits<streamsize>::max(), '\n'); //ignore input stream if invalid
				cout << "\nPlease enter a valid starting amount: "; 
			}
			else {
				validInput = true; //Set validInput to true if input is valid
			}

		}
		data.setStartingAmount(startAmount); //Set starting amount for UserData object from user input

		do {
			cout << "Monthly Deposit: $"; //Prompt user for monthly deposit
			cin >> deposit; //Read monthly deposit from user
			if (!(deposit > 0) || cin.fail()) { //Prompt user to enter input again if invalid
				cin.clear(); //Clear input to prompt user again
				cin.ignore(numeric_limits<streamsize>::max(), '\n'); //ignore input stream if invalid
				cout << "\nPlease enter a valid deposit amount: ";
				validInput = false; //Set validInput to false if input is invallid
			}
			else {
				validInput = true; //Set validInput to true if input is valid
			}
		} while (!validInput); //Loop if monthly deposit is invalid
		data.setMonthlyDeposit(deposit); //Set monthly deposit for UserData object from user input

		do {
			cout << "Annual Interest: %"; //Prompt user for annual interest
			cin >> interest; //Read interest from user
			if (!(interest > 0) || cin.fail()) { //Prompt user again if input is invalid
				cin.clear(); //Clear input to prompt user again
				cin.ignore(numeric_limits<streamsize>::max(), '\n'); //ignore input stream if invalid
				cout << "\nPlease enter a valid interest rate: ";
				validInput = false; //Set validInput to false if input is invalid
			}
			else {
				validInput = true; //Set validInput to true if input is valid
			}
		} while (!validInput); //Loop if interest is invalid
		data.setInterestRate(interest); //Set interest rate for UserData object from user input

		do {
			cout << "Number of Years: "; //Prompt user for amount of years
			cin >> year; //Read year from user
			if (!(year > 0) || cin.fail()) { //Prompt user again if input it invalid
				cin.clear(); //Clear input to prompt user again
				cin.ignore(numeric_limits<streamsize>::max(), '\n'); //ignore input stream if invalid
				cout << "\nPlease enter a valid number of years: ";
				validInput = false; //Set validInput to false if input is invalid
			}
			else {
				validInput = true; //Set validInput to true if input is valid
			}
		} while (!validInput); //loop to test for valid input of years
		data.setYears(year); //Set years for UserData object from user input

		system("pause"); //Pause for user.
		reportOutput(data); //Call function to print reports.

		cout << "\nWould you like to enter another report?" << endl; //Prompt user to enter more data or quit program
		cout << "Enter 'y' to continue. Enter 'n' to quit: "; 
		cin >> userInput; //Read program choice from user
		while ((userInput != 'y') && (userInput != 'n')) { //Loop if user input is not 'y' or 'n'
			cin.clear(); //clear input and prompt user for input
			cin.ignore(numeric_limits<streamsize>::max(), '\n'); //ignore input stream if invalid
			cout << "\nPlease enter y to continue or n to quit: ";
			cin >> userInput;
		}
	}
}

//main function
int main()
{
	userEntry(); //Call function to prompt user input
	cout << endl << "Exiting"; //End program

	return 0;
}