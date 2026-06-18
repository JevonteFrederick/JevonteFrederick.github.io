//By: Jevonte Frederick
//5/23/26

package interestCalc;

public class UserData {
	//User input
	private double startAmount = 0; //Starting amount
	private double depositAmount = 0; //Monthly deposit amount
	private double interest = 0; //Interest rate
	private int years = 0; //Total years for interest report

	//Calculations from user input for report
	private double totalBalance = 0; //Total balance
	private double interestTotal = 0; //Total interest
	private double interestMonthly = 0; //Monthly interest
	
	//Setter methods
	public void setStartingAmount(double startAmount) { //Set initial amount
		this.startAmount = startAmount;
	}
	public void setMonthlyDeposit(double depositAmount) { //Set monthly deposit
		this.depositAmount = depositAmount;
	}
	public void setInterestRate(double interest) { //Set interest rate
		this.interest = (interest / 100);
	}
	public void setYears(int years) { //Set years
		this.years = years;
	}
	public void setInitialTotalBalance() { //Initialize total balance
		totalBalance = startAmount;
	}
	public void setInitialInterestTotal() { //Initialize interest total to 0
		interestTotal = 0.0;
	}

	//Getter methods
	public int getYears() { //Return years
		return years;
	}
	public double getTotalBalance() { //Return total balance
		return totalBalance;
	}
	public double getInterestTotal() { //Return total interest
		return interestTotal;
	}
	
	//Calculation methods
	public void calculateTotalBalance() { //Calculate total balance without monthly deposits
		totalBalance = totalBalance + interestMonthly; //Add monthly interest to total balance 
	}
	public void calculateTotalBalanceMonthlyDeposit() { //Calculate total balance with monthly deposits
		totalBalance = totalBalance + depositAmount; //Add monthly deposit to total balance
	}
	public void calculateInterestTotal() { //Calculate total interest
		interestTotal = interestTotal + interestMonthly; //Add monthly interest to interest total
	}
	public void calculateInterestMonthly() { //Calculate monthly interest
		interestMonthly = totalBalance * (interest / 12.0); //Calculate interest for current month
	}

}
