module de.gamejam {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;

  opens de.gamejam to javafx.fxml;
  exports de.gamejam;
}