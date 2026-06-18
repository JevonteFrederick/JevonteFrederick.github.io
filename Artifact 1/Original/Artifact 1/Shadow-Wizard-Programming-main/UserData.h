//Jevonte Frederick
//4/5/25

#pragma once
#ifndef USERDATA_H
#define USERDATA_H

class UserData {
	public:
		void setStartingAmount(double t_startAmount); //Set initial amount
		void setMonthlyDeposit(double t_depositAmount); //Set monthly deposit
		void setInterestRate(double t_interest); //Set interest rate
		void setYears(int t_years); //Set years

		int getYears() ; //Return years for loop statments

		void setInitialTotalBalance(); //Set initial total balance for report
		void setTotalBalance(); //Calculate total balance without monthly deposits
		void setTotalBalanceMonthly(); //Calculate total balance with monthly deposits
		void setInitialInterestTotal(); //Initialize interest total for year
		void setInterestTotal(); //Calculate total interest
		void setInterestMonthly(); //Calculate monthly interest

		double getTotalBalance(); //Return total balance
		double getInterestTotal(); //Return total interest

	private:
		double m_startAmount = 0; //Starting amount
		double m_depositAmount = 0; //Monthly deposit amound
		double m_interest = 0; //Interest rate
		int m_years = 0; //Total years for interest report

		double m_totalBalance = 0; //Total balance
		double m_interestTotal = 0; //Total interest
		double m_interestMonthly = 0; //Monthly interest
};

#endif