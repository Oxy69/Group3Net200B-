module bb.bloodbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens bb.bloodbank to javafx.fxml;
    exports bb.bloodbank;
}