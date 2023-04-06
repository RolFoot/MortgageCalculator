module com.project.mortgagecalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires commons.math3;

    opens com.project.mortgagecalculator to javafx.fxml;
    exports com.project.mortgagecalculator;
}