//By: Jevonte Frederick
//5/23/26

package interestCalc;

public class DataCalculation {
	
	//Method to print reports
	public void printReport(UserData data, int reportType) {
		//Report header
		System.out.println("\n----------------------------------------------------------------------"); 
		
		//reportType = 0 determines if report is without monthly deposits. 
		//reportType = 1 determines if report is with monthly deposits
		if (reportType == 0) {
			System.out.println("	Balance and interest without additional Monthly Deposits");
		}
		else {
			System.out.println("	Balance and interest with additional Monthly Deposits");
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.printf("%-10s%30s%30s%n", "year", "Year End Balance", "Year End Interest");
		
		

		data.setInitialTotalBalance(); //Initialize total balance for report
		
		//Loop for each year of user input
		for (int countYear = 1; countYear <= data.getYears(); countYear++) { 
			calculateYear(data, reportType); //Call method to calculate one year of interest
	
			System.out.printf("%-15d%10s%15.2f%14s%16.2f%n", countYear, "$", data.getTotalBalance(), "$", data.getInterestTotal());
		}
	}
	
	//Method to calculate interest for a year.
	public void calculateYear(UserData data, int reportType) {
		data.setInitialInterestTotal(); //Set interest total to 0
		
		//Loop each month of the year
		for (int countMonth = 1; countMonth <= 12; countMonth++) { 
			if (reportType == 1) { //If report type = 1, add monthly deposits to balance
				data.calculateTotalBalanceMonthlyDeposit(); //Calculate monthly deposit added to balance			
			}
			data.calculateInterestMonthly(); //Calculate interest per month
			data.calculateTotalBalance(); //Calculate total balance
			data.calculateInterestTotal(); //Calculate total interest
		}
	}
}
