package calculations;

public class Calculations {

    private static final int MONTHS_IN_YEAR = 12;

    //static Mortgage mortgage = new Mortgage();

    public double calculateMonthlyPayment(Mortgage mortgage) {
        if (mortgage.getIsAnnuity())
            return calculateMonthlyPaymentAnnuity(mortgage);
        else if (mortgage.getIsLinear())
            return calculateMonthlyPaymentLinear(mortgage);
        else
            return 0;
    }

    public double calculateMonthlyPaymentAnnuity(Mortgage mortgage) {
        double monthlyPayment;

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        double term = (double) mortgage.getLoanTerm() * MONTHS_IN_YEAR;

        monthlyPayment = value * (interest * Math.pow(1 + interest, term)) / (Math.pow(1 + interest, term) - 1);


        return monthlyPayment;
    }

    public double calculateMonthlyPaymentLinear(Mortgage mortgage) {
        double monthlyPayment;

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        double term = (double) mortgage.getLoanTerm() * MONTHS_IN_YEAR;

        double currentMonth = 1;

        // P = (L / n) + (L - (m - 1) x (L / n)) x i
        // Where:
        //P = the monthly payment
        //L = the loan amount
        //n = the total number of months in the loan term
        //m = the number of the current month
        //i = the interest rate per month (annual interest rate divided by 12)

        monthlyPayment = (value / term) + (value - (currentMonth - 1) * (value / term)) * interest;

        return monthlyPayment;
    }
}
