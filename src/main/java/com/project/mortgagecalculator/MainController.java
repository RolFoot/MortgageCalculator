package com.project.mortgagecalculator;

import calculations.Calculations;
import calculations.Mortgage;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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
    private Button calculateBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private Button viewBtn;

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

            mortgage.setDelayFrom(Integer.parseInt(delayFromTxt.getText()));
            mortgage.setDelayTo(Integer.parseInt(delayToTxt.getText()));
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

        mortgage.setMonthlyPayment(calculations.calculateMonthlyPayment(mortgage));

        System.out.println("Monthly payment: " + mortgage.getMonthlyPayment());
    }

    public void export()
    {
        getInfoLoan();

        double totalSum = mortgage.getTotalSum();
        double totalPayment = mortgage.getTotalPayment();
        double monthlyPayment = mortgage.getMonthlyPayment();
        double totalInterest = mortgage.getTotalInterest();
        double leftToPay = totalSum - monthlyPayment;




        System.out.println("Monthly payment: " + mortgage.getMonthlyPayment());
    }

}