package calculations;

public class Calculations {

    private static final int MONTHS_IN_YEAR = 12;


    public double calculateMonthlyPaymentAnnuity(Mortgage mortgage) {
        double monthlyPayment;

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        double term = (double) mortgage.getLoanTerm() * MONTHS_IN_YEAR;

        monthlyPayment = value * (interest * Math.pow(1 + interest, term)) / (Math.pow(1 + interest, term) - 1);


        return monthlyPayment;
    }

    public double[] calculateMonthlyPaymentLinear(Mortgage mortgage) {
        double[] monthlyPayments = new double[mortgage.getLoanTerm() * MONTHS_IN_YEAR];

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        double term = (double) mortgage.getLoanTerm() * MONTHS_IN_YEAR;

        int currentMonth = 1;

        // P = (L / n) + (L - (m - 1) x (L / n)) x i
        // Where:
        //P = the monthly payment
        //L = the loan amount
        //n = the total number of months in the loan term
        //m = the number of the current month
        //i = the interest rate per month (annual interest rate divided by 12)

        for(int i = 0; i < term; currentMonth++, i++)
        {
            monthlyPayments[i] = (value / term) + (value - (currentMonth - 1) * (value / term)) * interest;
        }

        return monthlyPayments;
    }



    public double calculateLoanPart(Mortgage mortgage)
    {
        // Loan part (%)


        return 0;
    }

    public double calculateInterestPart(Mortgage mortgage) {
        // Interest part (%)
        return 0;
    }

    public double[] loanBalance(Mortgage mortgage) {
        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        int term = mortgage.getLoanTerm() * MONTHS_IN_YEAR;
        double monthlyPayment = mortgage.getMonthlyPaymentAnnuity();

        // Loan Balance = Previous Loan Balance - Monthly Payment + (Previous Loan Balance * Monthly Interest Rate)

        double[] balance = new double[term];
        balance[0] = value;
        double monthlyInterest;

        for (int i = 1; i < term; i++) {
            monthlyInterest = balance[i-1] * interest;
            balance[i] = balance[i-1] - monthlyPayment + monthlyInterest;
        }

        return balance;
    }
}
