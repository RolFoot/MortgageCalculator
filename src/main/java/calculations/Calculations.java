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



    public double[] calculateLoanPartAnnuity(Mortgage mortgage)
    {
        // Loan part (%)

        double monthlyPayment = mortgage.getMonthlyPaymentAnnuity();
        double[] loanPart = new double[mortgage.getLoanTerm() * MONTHS_IN_YEAR];

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;

        double[] monthlyBalance = mortgage.getMonthlyBalance();

        for(int i = 0; i < (mortgage.getLoanTerm() * MONTHS_IN_YEAR); i++)
        {
            loanPart[i] = (monthlyPayment - (monthlyBalance[i] * interest)) / monthlyPayment * 100;
        }

        return loanPart;
    }

    public double[] calculateLoanPartLinear(Mortgage mortgage)
    {
        // Loan part (%)

        double[] monthlyPayments = mortgage.getMonthlyPaymentLinear();
        double[] loanPart = new double[mortgage.getLoanTerm() * MONTHS_IN_YEAR];

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;

        double[] monthlyBalance = mortgage.getMonthlyBalance();

        for(int i = 0; i < (mortgage.getLoanTerm() * MONTHS_IN_YEAR); i++)
        {
            loanPart[i] = (monthlyPayments[i] - (monthlyBalance[i] * interest)) / monthlyPayments[i] * 100;
        }

        return loanPart;
    }

    public double[] calculateInterestPartAnnuity(Mortgage mortgage) {
        // Interest part (%)

        double[] interestPart = new double[mortgage.getLoanTerm() * MONTHS_IN_YEAR];
        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;

        double monthlyPayment = mortgage.getMonthlyPaymentAnnuity();

        double[] monthlyBalance = mortgage.getMonthlyBalance();

        double[] loanPart = mortgage.getLoanPart();

        for(int i = 0; i < (mortgage.getLoanTerm() * MONTHS_IN_YEAR); i++)
        {
            interestPart[i] = (monthlyPayment - (loanPart[i]/100 * monthlyPayment))/monthlyPayment*100;
        }

        return interestPart;
    }

    public double[] calculateInterestPartLinear(Mortgage mortgage) {
        // Interest part (%)

        double[] interestPart = new double[mortgage.getLoanTerm() * MONTHS_IN_YEAR];
        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;

        double[] monthlyPayment = mortgage.getMonthlyPaymentLinear();

        double[] monthlyBalance = mortgage.getMonthlyBalance();

        double[] loanPart = mortgage.getLoanPart();

        for(int i = 0; i < (mortgage.getLoanTerm() * MONTHS_IN_YEAR); i++)
        {
            interestPart[i] = (monthlyPayment[i] - (loanPart[i]/100 * monthlyPayment[i]))/monthlyPayment[i]*100;
        }

        return interestPart;

    }

    public double[] loanBalanceAnnuity(Mortgage mortgage) {
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

    public double[] loanBalanceLinear(Mortgage mortgage) {
        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        int term = mortgage.getLoanTerm() * MONTHS_IN_YEAR;

        double[] monthlyPayment = mortgage.getMonthlyPaymentLinear();

        // Loan Balance = Previous Loan Balance - Monthly Payment + (Previous Loan Balance * Monthly Interest Rate)

        double[] balance = new double[term];
        balance[0] = value;
        double monthlyInterest;

        for (int i = 1; i < term; i++) {
            monthlyInterest = balance[i-1] * interest;
            balance[i] = balance[i-1] - monthlyPayment[i] + monthlyInterest;
        }

        return balance;
    }
}
