//Jevonte Frederick
//4/5/25

#include "DataCalculation.h"
#include "UserData.h"
#include <iomanip>
#include <iostream>
using namespace std;

void DataCalculation::printReport(UserData t_data) {
	cout << "\n----------------------------------------------------------------------" << endl; //Report header
	cout << "	Balance and interest without additional Monthly Deposits" << endl;
	cout << "----------------------------------------------------------------------" << endl;
	cout << left << setw(10) << "Year"; 
	cout << right << setw(25) << "Year End Balance";
	cout << right << setw(30) << "Year End Interest" << endl;

	t_data.setInitialTotalBalance(); //Initialize total balance for report
	for (int countYear = 1; countYear <= t_data.getYears(); countYear++) { //Loop for each year of user input
		t_data.setInitialInterestTotal(); //Set interest total to 0
		for (int countMonth = 1; countMonth <= 12; countMonth++) { //Loop each month of the year
			t_data.setInterestMonthly(); //Calculate interest per month
			t_data.setTotalBalance(); //Calculate total balance
			t_data.setInterestTotal(); //Calculate total interest
		}

		//Print results for year
		cout << setprecision(2) << fixed;
		cout << left << setw(10) << countYear; //Print current year
		cout << right << setw(10) << "$" << setw(15) << t_data.getTotalBalance(); //Print current year's total balance
		cout << right << setw(14) << "$" << setw(16) << t_data.getInterestTotal() << endl; //Print current year's total interest
	}

	cout << endl;
}

void DataCalculation::printMonthlyDepositsReport(UserData t_data) {
	cout << "----------------------------------------------------------------------" << endl; //Report header
	cout << "	Balance and interest with additional Monthly Deposits" << endl;
	cout << "----------------------------------------------------------------------" << endl;
	cout << left << setw(10) << "Year" << setfill(' ');
	cout << right << setw(25) << "Year End Balance" << setfill(' ');
	cout << right << setw(30) << "Year End Interest" << setfill(' ') << endl;

	t_data.setInitialTotalBalance(); //Initialize total balance for report
	for (int countYear = 1; countYear <= t_data.getYears(); countYear++) { //Loop for each year of user input
		t_data.setInitialInterestTotal(); //set interest total to 0
		for (int countMonth = 1; countMonth <= 12; countMonth++) {  //Loop each month of the year
			t_data.setTotalBalanceMonthly(); //Calculate monthly deposit added to balance
			t_data.setInterestMonthly(); //Calculate interest per month
			t_data.setTotalBalance(); //Calculate total balance
			t_data.setInterestTotal(); //Calculate total interest
		}

		//Print results for year
		cout << setprecision(2) << fixed;
		cout << left << setw(10) << countYear; //Print current year
		cout << right << setw(10) << "$" << setw(15) << t_data.getTotalBalance(); //Print current year's total balance
		cout << right << setw(14) << "$" << setw(16) << t_data.getInterestTotal() << endl; //Print current year's total interest
	}

	system("pause"); //Pause for user
}