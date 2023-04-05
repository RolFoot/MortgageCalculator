module com.project.mortgagecalculator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.project.mortgagecalculator to javafx.fxml;
    exports com.project.mortgagecalculator;
}