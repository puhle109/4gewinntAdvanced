package de.gamejam;

import java.io.IOException;
import java.util.LinkedList;

import de.gamejam.control.GameController;
import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.Grid;
import de.gamejam.model.Player;
import de.gamejam.model.ui_element.ChipView;
import de.gamejam.model.ui_element.InputView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

  private static Scene scene;

  private GameController gameController = new GameController();

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    stage.setTitle("Super tolles Spiel");
    gameController.createPlayer(ChipColor.BLUE);
    gameController.createPlayer(ChipColor.RED);

    BorderPane root = new BorderPane();

    GridPane chipQueuePane = new GridPane();
    fillQueuePane(chipQueuePane);

    GridPane mainPane = new GridPane();
    fillMainPane(mainPane);

    GridPane inputPane = new GridPane();
    fillInputPane(inputPane);

    root.setCenter(mainPane);
    root.setBottom(chipQueuePane);
    root.setTop(inputPane);

    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private void fillInputPane(GridPane inputPane) {
    Grid grid = gameController.getGridController().getGrid();

    for (int colNumber = 0; colNumber < grid.getChips().size(); colNumber++) {
      inputPane.add(new InputView(), colNumber, 0);
    }
  }

  private void fillMainPane(GridPane mainPane) {
      mainPane.setGridLinesVisible(true);
    Grid grid = gameController.getGridController().getGrid();

    for (int colNumber = 0; colNumber < grid.getChips().size(); colNumber++) {
      LinkedList<ChipView> column = grid.getChips().get(colNumber);
      for (int rowNumber = 0; rowNumber < column.size(); rowNumber++) {
        mainPane.add(column.get(rowNumber), colNumber, rowNumber);
      }
    }
  }

  private void fillQueuePane(GridPane queuePane) {
    Player player = gameController.getActivePlayer();

    int colCount = 0;
    // Zukünftige Chips anfügen
    for (Chip chip : player.getChipQueue().getNextChips()) {
      ChipView chipView = new ChipView();
      chipView.setOpacity(.5);
      chipView.setChip(chip);
      queuePane.add(chipView, colCount, 0);
      colCount++;
    }

    // Verwendbare Chips anfügen
    for (Chip chip : player.getChipQueue().getUsableChips()) {
      final ChipView chipView = new ChipView();
      chipView.setOnMouseClicked(mouseEvent -> {

        player.setChoosenChip(chipView);

        //alle unselected
//        queuePane.getChildren()

        // ausgewählen selecten
        chipView.setSelected();
      });
      chipView.setChip(chip);
      queuePane.add(chipView, colCount, 0);
      colCount++;
    }
  }
}