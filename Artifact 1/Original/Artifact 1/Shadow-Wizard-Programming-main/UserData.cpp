//Jevonte Frederick
//4/5/25

#include "UserData.h"

using namespace std;

void UserData::setStartingAmount(double t_startAmount) { //Set initial amount
	m_startAmount = t_startAmount;
}
void UserData::setMonthlyDeposit(double t_depositAmount) { //Set monthly deposit
	m_depositAmount = t_depositAmount;
}
void UserData::setInterestRate(double t_interest) { //Set interest rate
	m_interest = (t_interest / 100);
}
void UserData::setYears(int t_years) { //Set years
	m_years = t_years;
}


int UserData::getYears() { //Return years for loop statments
	return m_years;
}

void UserData::setInitialTotalBalance() { //Initialize total balance
	m_totalBalance = m_startAmount;
}
void UserData::setTotalBalance() { //Calculate total balance without monthly deposits
	m_totalBalance = m_totalBalance + m_interestMonthly; //Add monthly interest to total balance 
}
void UserData::setTotalBalanceMonthly() { //Calculate total balance with monthly deposits
	m_totalBalance = m_totalBalance + m_depositAmount; //Add monthly desposit to total balance
}
void UserData::setInitialInterestTotal() { //Initialize interest total to 0
	m_interestTotal = 0.0;
}
void UserData::setInterestTotal() { //Calculate total interest
	m_interestTotal = m_interestTotal + m_interestMonthly; //Add monthly interest to interest total
}
void UserData::setInterestMonthly() { //Calculate monthly interest
	m_interestMonthly = m_totalBalance * (m_interest / 12.0); //Calculate interest for current month
}

double UserData::getTotalBalance() { //Return total balance
	return m_totalBalance;
}
double UserData::getInterestTotal() { //Return total interest
	return m_interestTotal;
}