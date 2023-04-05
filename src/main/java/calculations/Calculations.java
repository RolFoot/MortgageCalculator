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
        double monthlyPayment = 0;

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();

        double term = (double) mortgage.getLoanTerm() * MONTHS_IN_YEAR;

        monthlyPayment = value * (interest * Math.pow(1 + interest, term)) / (Math.pow(1 + interest, term) - 1);



        return monthlyPayment;
    }

    public double calculateMonthlyPaymentLinear(Mortgage mortgage) {
        double monthlyPayment = 1;
        return monthlyPayment;
    }
}
