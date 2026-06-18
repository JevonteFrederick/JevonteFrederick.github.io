//Jevonte Frederick
//4/5/25

#pragma once
#ifndef DATACALCULATION_H
#define DATACALCULATION_H
#include "UserData.h"

class DataCalculation {
	public:
		void printReport(UserData t_data); //Function to print report without monthly deposit.

		void printMonthlyDepositsReport(UserData t_data); //Function to print report with monthly deposit.
};

#endif