package com.project.mortgagecalculator;

import calculations.ExcelData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mortgage Calculator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        ExcelData excelData = new ExcelData();
    }

    public static void main(String[] args) {
        launch();
    }
}