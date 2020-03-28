package de.gamejam;

import java.io.IOException;
import java.util.LinkedList;

import de.gamejam.control.GameController;
import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.Grid;
import de.gamejam.model.Player;
import de.gamejam.model.Winner;
import de.gamejam.model.ui_element.ChipView;
import de.gamejam.model.ui_element.InputView;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

  private static Scene scene;

  private GameController gameController = new GameController();
  private GridPane mainPane;
  private GridPane chipQueuePane;
  private Stage stage;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    this.stage = stage;
    stage.setTitle("Super tolles Spiel");
    gameController.createPlayer(ChipColor.BLUE);
    gameController.createPlayer(ChipColor.RED);

    BorderPane root = new BorderPane();

    chipQueuePane = new GridPane();
    fillQueuePane();

    mainPane = new GridPane();
    fillMainPane();

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
    Player activePlayer = gameController.getActivePlayer();
    inputPane.setGridLinesVisible(true);
    for (int colNumber = 0; colNumber < grid.getChips().size(); colNumber++) {
      final InputView inputView = new InputView(colNumber);
      inputView.setCursor(Cursor.CLOSED_HAND);
      inputView.setOnMouseEntered(mouseEvent -> {
        if(activePlayer.getChoosenChip() != null){
          inputView.setChip(activePlayer.getChoosenChip().getChip());
        }
      });
      inputView.setOnMouseExited(mouseEvent -> {
        inputView.setChip(null);
        inputView.setDefaultImage();
      });
      inputView.setOnMouseClicked(mouseEvent -> {
        clickUseChip(inputView.getColumn());
      });
      inputPane.add(inputView, colNumber, 0);
    }
  }

  private void clickUseChip(int column) {
    Player activePlayer = gameController.getActivePlayer();
    // Wenn kein Chip ausgewählt wurde
    if(activePlayer.getChoosenChip() == null){
      return;
    }
    gameController.useChip(column);
    showWin();
    fillMainPane();
    fillQueuePane();
  }

  private void showWin(){

    Winner winner = gameController.checkWin();

    String text = null;

    switch (winner){
      case RED:
        text = "Spieler Rot hat's gerockt!";
        break;
      case BLUE:
        text = "Spieler Blau hat's gerockt!";
        break;
      case BOTH:
        text = "Jeder gewinnt!";
        break;
      case DRAW:
        text = "Ihr habt's beide verkackt...";
        break;
      case NONE:
        return;
    }

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(this.stage);
    VBox dialogVbox = new VBox(20);
    dialogVbox.getChildren().add(new Text(text));
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  private void fillMainPane() {
    // Aufräumen...
    mainPane.getChildren().clear();

    mainPane.setGridLinesVisible(true);
    Grid grid = gameController.getGridController().getGrid();

    for (int colNumber = 0; colNumber < grid.getChips().size(); colNumber++) {
      LinkedList<ChipView> column = grid.getChips().get(colNumber);
      int gridRowNumber = 0;
      for (int rowNumber = column.size()-1; rowNumber >= 0; rowNumber--) {
        ChipView chipView = column.get(rowNumber);
        int finalColNumber = colNumber;
        chipView.setOnMouseClicked(mouseEvent -> clickUseChip(finalColNumber));
        mainPane.add(chipView, colNumber, gridRowNumber++);
      }
    }
  }

  private void fillQueuePane() {
    // Aufräumen
    chipQueuePane.getChildren().clear();

    Player player = gameController.getActivePlayer();

    int colCount = 0;
    // Zukünftige Chips anfügen
    for (Chip chip : player.getChipQueue().getNextChips()) {
      ChipView chipView = new ChipView();
      chipView.setOpacity(.5);
      chipView.setChip(chip);
      chipQueuePane.add(chipView, colCount, 0);
      colCount++;
    }

    // Verwendbare Chips anfügen
    for (Chip chip : player.getChipQueue().getUsableChips()) {
      final ChipView chipView = new ChipView();

      chipView.setOnMouseClicked(mouseEvent -> {
        player.setChoosenChip(chipView);
        //alle unselected
        setChipUnselected(chipQueuePane);
        // ausgewählen selecten
        chipView.setSelected();
      });
      chipView.setChip(chip);
      chipQueuePane.add(chipView, colCount, 0);
      colCount++;
    }
  }

  private void setChipUnselected(GridPane queuePane) {
    queuePane.getChildren().stream().forEach(node -> {
      if (node instanceof ChipView) {
        ((ChipView) node).setUnselected();
      }
    });
  }
}