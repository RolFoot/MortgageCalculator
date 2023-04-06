package calculations;

public class Calculations {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int MAX_TERM = 1200; // 100 years

    public double[] calculateMonthlyPayment(Mortgage mortgage) {
        double[] monthlyPayments = new double[MAX_TERM];
        double payment;

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        int term = mortgage.getLoanTerm();

        int currentMonth = 1;

        int delayFrom = mortgage.getDelayFrom();
        int delayTo = mortgage.getDelayTo();
        double delayInterest = mortgage.getDelayInterest() / 100 / MONTHS_IN_YEAR;

        boolean isAnnuity = mortgage.getIsAnnuity();

        if (delayTo - delayFrom < 1) {
            if (isAnnuity) {
                payment = value * (interest * Math.pow(1 + interest, term)) / (Math.pow(1 + interest, term) - 1);

                for (int i = 0; i < term; i++)
                    monthlyPayments[i] = payment;
            } else // linear
            {
                for (int i = 0; i < term; currentMonth++, i++) {
                    monthlyPayments[i] = (value / term) + (value - (currentMonth - 1) * (value / term)) * interest;
                }
            }
        } else {
            if (isAnnuity) {
                for (int i = 0; i < term; currentMonth++, i++) {
                    if (currentMonth >= delayFrom && currentMonth <= delayTo) {
                        monthlyPayments[i] = value * delayInterest;
                        term++;
                    } else // annuity loan formula
                        monthlyPayments[i] = value * (interest * Math.pow(1 + interest, term)) / (Math.pow(1 + interest, term) - 1);
                }
            } else //linear
            {
                for (int i = 0; i < term; currentMonth++, i++) {
                    if (currentMonth >= delayFrom && currentMonth <= delayTo) {
                        monthlyPayments[i] = value * delayInterest;
                        term++;
                    } else // linear loan formula
                        monthlyPayments[i] = (value / term) + (value - (currentMonth - 1) * (value / term)) * interest;
                }
            }

            mortgage.setLoanTerm(term);
        }

        return monthlyPayments;
    }

    public double[] calculateLoanPart(Mortgage mortgage) {
        // Loan part (%)

        double[] monthlyPayments = mortgage.getMonthlyPayment();
        int term = mortgage.getLoanTerm();
        double[] loanPart = new double[term];

        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;

        double[] monthlyBalance = mortgage.getMonthlyBalance();

        int delayFrom = mortgage.getDelayFrom();
        int delayTo = mortgage.getDelayTo();

        for (int i = 0; i < term; i++) {
            if ((i >= delayFrom && i <= delayTo) && (delayTo - delayFrom > 0))
                loanPart[i] = 0;
            else
                loanPart[i] = (monthlyPayments[i] - (monthlyBalance[i] * interest)) / monthlyPayments[i] * 100;
        }

        return loanPart;
    }

    public double[] calculateInterestPart(Mortgage mortgage) {
        // Interest part (%)

        double[] interestPart = new double[mortgage.getLoanTerm()];
        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;

        double[] monthlyPayment = mortgage.getMonthlyPayment();

        double[] loanPart = mortgage.getLoanPart();

        int delayFrom = mortgage.getDelayFrom();
        int delayTo = mortgage.getDelayTo();

        for (int i = 0; i < mortgage.getLoanTerm(); i++) {
            if ((i >= delayFrom && i <= delayTo) && (delayTo - delayFrom > 0))
                interestPart[i] = 0;
            else
                interestPart[i] = (monthlyPayment[i] - (loanPart[i] / 100 * monthlyPayment[i])) / monthlyPayment[i] * 100;
        }

        return interestPart;

    }

    public double[] loanBalance(Mortgage mortgage) {
        double interest = mortgage.getInterest() / 100 / MONTHS_IN_YEAR;
        double value = mortgage.getValue() - mortgage.getDownPayment();
        int term = mortgage.getLoanTerm();

        double[] monthlyPayment = mortgage.getMonthlyPayment();

        // Loan Balance = Previous Loan Balance - Monthly Payment + (Previous Loan Balance * Monthly Interest Rate)

        double[] balance = new double[term];
        balance[0] = value;
        double monthlyInterest;

        int delayFrom = mortgage.getDelayFrom();
        int delayTo = mortgage.getDelayTo();

        for (int i = 1; i < term; i++) {
            if ((i >= delayFrom && i <= delayTo) && (delayTo - delayFrom > 0)) {
                balance[i] = balance[i - 1];
            } else {
                monthlyInterest = balance[i - 1] * interest;

                balance[i] = balance[i - 1] - monthlyPayment[i] + monthlyInterest;
            }
        }

        return balance;
    }
}
