module com.example.zpo_lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.zpo_lab4 to javafx.fxml;
    exports application.zpo_lab4;
}