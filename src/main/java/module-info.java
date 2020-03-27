module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.gamejam to javafx.fxml;
    exports de.gamejam;
}