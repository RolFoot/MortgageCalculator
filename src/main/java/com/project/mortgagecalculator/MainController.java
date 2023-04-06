package com.project.mortgagecalculator;

import calculations.Calculations;
import calculations.ExcelData;
import calculations.Mortgage;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;



public class MainController {

    @FXML
    private TextField valueTxt;

    @FXML
    private TextField downPaymentTxt;

    @FXML
    private TextField interestTxt;

    @FXML
    private TextField loanTermTxt;

    @FXML
    private RadioButton annuityRBtn;

    @FXML
    private RadioButton linearRBtn;

    @FXML
    private TextField viewFromTxt;

    @FXML
    private TextField viewToTxt;

    @FXML
    private TextField delayFromTxt;

    @FXML
    private TextField delayToTxt;

    @FXML
    private LineChart chart;

    Mortgage mortgage = new Mortgage();

    Calculations calculations = new Calculations();

    public void getInfoLoan()
    {
        try {
            mortgage.setValue(Double.parseDouble(valueTxt.getText()));
            mortgage.setDownPayment(Double.parseDouble(downPaymentTxt.getText()));
            mortgage.setInterest(Double.parseDouble(interestTxt.getText()));
            mortgage.setLoanTerm(Integer.parseInt(loanTermTxt.getText()));
            mortgage.setAnnuity(annuityRBtn.isSelected());
            mortgage.setLinear(linearRBtn.isSelected());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void getInfoView()
    {
        try {
            mortgage.setViewFrom(Integer.parseInt(viewFromTxt.getText()));
            mortgage.setViewTo(Integer.parseInt(viewToTxt.getText()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void getInfoDelay()
    {
        try {
            mortgage.setDelayFrom(Integer.parseInt(delayFromTxt.getText()));
            mortgage.setDelayTo(Integer.parseInt(delayToTxt.getText()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void calculate()
    {
        getInfoLoan();

        if (mortgage.getIsAnnuity())
        {
            mortgage.setMonthlyPaymentAnnuity(calculations.calculateMonthlyPaymentAnnuity(mortgage));
            System.out.println("Monthly payment: " + mortgage.getMonthlyPaymentAnnuity());

            mortgage.setMonthlyBalance(calculations.loanBalanceAnnuity(mortgage));

            double[] monthlyBalance = mortgage.getMonthlyBalance();

            /*for(int i=0;i<(mortgage.getLoanTerm() * 12);i++)
            {
                System.out.println("Monthly balance: " + monthlyBalance[i]);
            }*/

            mortgage.setLoanPart(calculations.calculateLoanPartAnnuity(mortgage));

            double[] loanPart = mortgage.getLoanPart();

            /*for(int i=0;i<(mortgage.getLoanTerm() * 12);i++)
            {
                System.out.println("Loan part: " + loanPart[i]);
            }*/

            mortgage.setInterestPart(calculations.calculateInterestPartAnnuity(mortgage));
            double[] interestPart = mortgage.getInterestPart();

            for (int i = 0; i < (mortgage.getLoanTerm() * 12); i++) {
                System.out.println("Interest part: " + interestPart[i]);
            }

        }
        else if (mortgage.getIsLinear())
        {
            mortgage.setMonthlyPaymentLinear(calculations.calculateMonthlyPaymentLinear(mortgage));

            double[] monthlyPayments = mortgage.getMonthlyPaymentLinear();

            /*for(int i=0;i<(mortgage.getLoanTerm() * 12);i++)
            {
                System.out.println("Monthly payment: " + monthlyPayments[i]);
            }*/

            mortgage.setMonthlyBalance(calculations.loanBalanceLinear(mortgage));

            double[] monthlyBalance = mortgage.getMonthlyBalance();

            /*for(int i=0;i<(mortgage.getLoanTerm() * 12);i++)
            {
                System.out.println("Monthly balance: " + monthlyBalance[i]);
            }*/

            mortgage.setLoanPart(calculations.calculateLoanPartLinear(mortgage));

            double[] loanPart = mortgage.getLoanPart();

            /*for(int i=0;i<(mortgage.getLoanTerm() * 12);i++)
            {
                System.out.println("Loan part: " + loanPart[i]);
            }*/

            mortgage.setInterestPart(calculations.calculateInterestPartLinear(mortgage));
            double[] interestPart = mortgage.getInterestPart();

            for (int i = 0; i < (mortgage.getLoanTerm() * 12); i++) {
                System.out.println("Interest part: " + interestPart[i]);
            }
        }
    }

    public void export() throws IOException {
        ExcelData excelData = new ExcelData(mortgage);
    }

}