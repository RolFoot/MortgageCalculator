package com.project.mortgagecalculator;

import calculations.Calculations;
import calculations.ExcelData;
import calculations.Mortgage;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
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
    private TextField delayInterestTxt;

    @FXML
    private LineChart chart;

    Mortgage mortgage = new Mortgage();

    Calculations calculations = new Calculations();

    private boolean calculated = false;

    public void getInfoLoan() {
        try {
            mortgage.setValue(Double.parseDouble(valueTxt.getText()));
            mortgage.setDownPayment(Double.parseDouble(downPaymentTxt.getText()));
            mortgage.setInterest(Double.parseDouble(interestTxt.getText()));
            mortgage.setLoanTerm(Integer.parseInt(loanTermTxt.getText())*12);
            mortgage.setAnnuity(annuityRBtn.isSelected());
            mortgage.setLinear(linearRBtn.isSelected());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Input");
            a.setContentText("Error: " + e);
            a.show();
        }
    }

    public void getInfoView() {
        try {
            mortgage.setViewFrom(Integer.parseInt(viewFromTxt.getText()));
            mortgage.setViewTo(Integer.parseInt(viewToTxt.getText()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Input");
            a.setContentText("Error: " + e);
            a.show();
        }
    }

    public void getInfoDelay() {
        try {
            mortgage.setDelayFrom(Integer.parseInt(delayFromTxt.getText()));
            mortgage.setDelayTo(Integer.parseInt(delayToTxt.getText()));
            mortgage.setDelayInterest(Double.parseDouble(delayInterestTxt.getText()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Input");
            a.setContentText("Error: " + e);
            a.show();
        }
    }

    public void calculate() {
        getInfoLoan();
        getInfoDelay();

        if (mortgage.getIsAnnuity()) {
            mortgage.setMonthlyPaymentAnnuity(calculations.calculateMonthlyPaymentAnnuity(mortgage));

            mortgage.setMonthlyBalance(calculations.loanBalanceAnnuity(mortgage));

            mortgage.setLoanPart(calculations.calculateLoanPartAnnuity(mortgage));

            mortgage.setInterestPart(calculations.calculateInterestPartAnnuity(mortgage));

            calculated = true;
        } else if (mortgage.getIsLinear()) {
            mortgage.setMonthlyPaymentLinear(calculations.calculateMonthlyPaymentLinear(mortgage));

            mortgage.setMonthlyBalance(calculations.loanBalanceLinear(mortgage));

            mortgage.setLoanPart(calculations.calculateLoanPartLinear(mortgage));

            mortgage.setInterestPart(calculations.calculateInterestPartLinear(mortgage));

            calculated = true;
        }
    }

    public void export() throws IOException {
        if (!calculated)
            calculate();

        new ExcelData(mortgage);
    }

}