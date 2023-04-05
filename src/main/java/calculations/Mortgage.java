package calculations;

import com.project.mortgagecalculator.*;
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
    private double monthlyPayment;
    private double totalPayment;
    private double totalInterest;
    private double totalDelay;
    private double totalFilter;
    private double totalSum;


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
    public void setIsAnnuity(boolean isAnnuity){
        this.isAnnuity = isAnnuity;
    }
    public void setIsLinear(boolean isLinear){
        this.isLinear = isLinear;
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
    public void setMonthlyPayment(double monthlyPayment){
        this.monthlyPayment = monthlyPayment;
    }
    public void setTotalPayment(double totalPayment){
        this.totalPayment = totalPayment;
    }
    public void setTotalInterest(double totalInterest){
        this.totalInterest = totalInterest;
    }
    public void setTotalDelay(double totalDelay){
        this.totalDelay = totalDelay;
    }
    public void setTotalFilter(double totalFilter){
        this.totalFilter = totalFilter;
    }
    public void setTotalSum(double totalSum){
        this.totalSum = totalSum;
    }

    public void setAnnuity(boolean isAnnuity){
        this.isAnnuity = isAnnuity;
    }
    public void setLinear(boolean isLinear){
        this.isLinear = isLinear;
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
    public double getMonthlyPayment(){
        return monthlyPayment;
    }
    public double getTotalPayment(){
        return totalPayment;
    }
    public double getTotalInterest(){
        return totalInterest;
    }
    public double getTotalDelay(){
        return totalDelay;
    }
    public double getTotalFilter(){
        return totalFilter;
    }
    public double getTotalSum(){
        return totalSum;
    }

}
