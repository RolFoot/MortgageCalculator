package calculations;

public class Mortgage {
    private double value;
    private double downPayment;
    private double interest;
    private int loanTerm;
    private boolean isAnnuity;
    private boolean isLinear;
    private int viewFrom;
    private int viewTo;
    private int delayFrom;
    private int delayTo;
    private double delayInterest;
    private double[] monthlyPayments;

    private double[] monthlyBalance;

    private double[] loanPart;
    private double[] interestPart;


    // Setters
    public void setValue(double value){
        this.value = value;
    }
    public void setDownPayment(double downPayment){
        this.downPayment = downPayment;
    }
    public void setInterest(double interest){
        this.interest = interest;
    }
    public void setLoanTerm(int loanTerm){
        this.loanTerm = loanTerm;
    }
    public void setViewFrom(int viewFrom){
        this.viewFrom = viewFrom;
    }
    public void setViewTo(int viewTo){
        this.viewTo = viewTo;
    }
    public void setDelayFrom(int delayFrom){
        this.delayFrom = delayFrom;
    }
    public void setDelayTo(int delayTo){
        this.delayTo = delayTo;
    }
    public void setDelayInterest(double delayInterest){
        this.delayInterest = delayInterest;
    }
    public void setMonthlyPayment(double[] monthlyPayments){
        this.monthlyPayments = monthlyPayments;
    }

    public void setAnnuity(boolean isAnnuity){
        this.isAnnuity = isAnnuity;
    }
    public void setLinear(boolean isLinear){
        this.isLinear = isLinear;
    }

    public void setMonthlyBalance(double[] monthlyBalance){
        this.monthlyBalance = monthlyBalance;
    }

    public void setLoanPart(double[] loanPart){
        this.loanPart = loanPart;
    }

    public void setInterestPart(double[] interestPart){
        this.interestPart = interestPart;
    }



    // Getters
    public double getValue(){
        return value;
    }
    public double getDownPayment(){
        return downPayment;
    }
    public double getInterest(){
        return interest;
    }
    public int getLoanTerm(){
        return loanTerm;
    }
    public boolean getIsAnnuity(){
        return isAnnuity;
    }
    public boolean getIsLinear(){
        return isLinear;
    }
    public int getViewFrom(){
        return viewFrom;
    }
    public int getViewTo(){
        return viewTo;
    }
    public int getDelayFrom(){
        return delayFrom;
    }
    public int getDelayTo(){
        return delayTo;
    }
    public double getDelayInterest(){
        return delayInterest;
    }
    public double[] getMonthlyPayment(){return monthlyPayments;}
    public double[] getMonthlyBalance(){return monthlyBalance;}

    public double[] getLoanPart(){
        return loanPart;
    }

    public double[] getInterestPart(){
        return interestPart;
    }
}
