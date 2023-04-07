package com.project.mortgagecalculator;

import calculations.Calculations;
import calculations.ExcelData;
import calculations.Mortgage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.apache.commons.math3.util.Precision;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


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
    private LineChart<Integer,Number> chart;

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

        mortgage.setMonthlyPayment(calculations.calculateMonthlyPayment(mortgage));
        mortgage.setMonthlyBalance(calculations.loanBalance(mortgage));
        mortgage.setLoanPart(calculations.calculateLoanPart(mortgage));
        mortgage.setInterestPart(calculations.calculateInterestPart(mortgage));

        calculated = true;
    }

    public void export() throws IOException {
        if (!calculated)
            calculate();

        new ExcelData(mortgage);
    }

    public void viewChart() {
        if (!calculated)
            calculate();

        getInfoView();

        XYChart.Series<Integer,Number> series1 = new XYChart.Series<>();
        XYChart.Series<Integer,Number> series2 = new XYChart.Series<>();
        XYChart.Series<Integer,Number> series3 = new XYChart.Series<>();

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();

        series1.setName("Total payment");
        series2.setName("Interest payment");
        series3.setName("Loan payment");

        for(int i = mortgage.getViewFrom(); i < mortgage.getViewTo(); i++){
            series1.getData().add(new XYChart.Data<>(i+1, Precision.round(mortgage.getMonthlyPayment()[i], 2)));
            series2.getData().add(new XYChart.Data<>(i+1, Precision.round(((mortgage.getInterestPart()[i]*mortgage.getMonthlyPayment()[i])/100), 2)));
            series3.getData().add(new XYChart.Data<>(i+1, Precision.round(((mortgage.getLoanPart()[i]*mortgage.getMonthlyPayment()[i])/100), 2)));

            System.out.println(i+1 + " " + mortgage.getMonthlyPayment()[i] + " " + mortgage.getInterestPart()[i] + " " + mortgage.getLoanPart()[i]);
        }
        List<XYChart.Series<Integer, Number>> seriesList = Arrays.asList(series2, series1, series3);
        ObservableList<XYChart.Series<Integer, Number>> observableSeriesList = FXCollections.observableList(seriesList);
        chart.setData(observableSeriesList);

    }
}