package de.gamejam;

import java.io.IOException;
import java.util.LinkedList;

import de.gamejam.control.GameController;
import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.ChipType;
import de.gamejam.model.Grid;
import de.gamejam.model.Player;
import de.gamejam.model.Winner;
import de.gamejam.model.ui_element.ChipView;
import de.gamejam.model.ui_element.InputView;
import de.gamejam.model.ui_element.SelectableChipView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static de.gamejam.model.ui_element.ChipView.IMAGE_SIZE;

public class App extends Application {

  private static Scene scene;

  private GameController gameController = new GameController();
  private GridPane gridPane;
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

    gridPane = new GridPane();
    BorderPane.setMargin(gridPane, new Insets(5, 5, 5, 5));
    fillGridPane();

    GridPane inputPane = new GridPane();
    BorderPane.setMargin(inputPane, new Insets(5, 5, 5, 5));
    fillInputPane(inputPane);

    BorderPane mainPane = new BorderPane();
    mainPane.setCenter(gridPane);
    mainPane.setBottom(chipQueuePane);
    mainPane.setTop(inputPane);

    root.setCenter(mainPane);

    Pane explainBoard = createExplainBoard();
                                                //(top, right, bottom, left)
    BorderPane.setMargin(explainBoard, new Insets(10, 5, 10, 10));
    root.setLeft(explainBoard);
    scene = new Scene(root);

    stage.setScene(scene);
    stage.setHeight(stage.getHeight() + 10);
    stage.setWidth(stage.getWidth() + 10);
    stage.show();
  }

  private void fillInputPane(GridPane inputPane) {
    inputPane.setAlignment(Pos.CENTER);
    Grid grid = gameController.getGridController().getGrid();
    Player activePlayer = gameController.getActivePlayer();
    inputPane.setGridLinesVisible(true);
    for (int colNumber = 0; colNumber < grid.getChips().size(); colNumber++) {
      final InputView inputView = new InputView(colNumber);
      inputView.setCursor(Cursor.CLOSED_HAND);
      inputView.setOnMouseEntered(mouseEvent -> {
        if (activePlayer.getChoosenChip() != null) {
          inputView.setChip(activePlayer.getChoosenChip().getChip());
        }
      });
      inputView.setOnMouseExited(mouseEvent -> {
        inputView.setChip(null);
      });
      inputView.setOnMouseClicked(mouseEvent -> {
        clickUseChip(inputView.getColumn());
      });
      inputPane.add(inputView, colNumber, 0);
    }
  }

  private Pane createExplainBoard() {
    BorderPane pane = new BorderPane();

    GridPane images = new GridPane();
    for (int i = 0; i < ChipType.values().length; i++) {
      ChipType chipType = ChipType.values()[i];
      String filename = chipType.getFilename();
      Image image = new Image(
          getClass().getResource("/img/" + gameController.getActivePlayer().getColor().getValue() + "_" + filename).toExternalForm());
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(IMAGE_SIZE);
      imageView.setFitWidth(IMAGE_SIZE);
      images.add(imageView, 0, i);
      images.add(new Text(chipType.getDescription()), 1, i);
    }
    pane.setCenter(images);

    return pane;
  }

  private void clickUseChip(int column) {
    Player activePlayer = gameController.getActivePlayer();
    // Wenn kein Chip ausgewählt wurde
    if (activePlayer.getChoosenChip() == null) {
      return;
    }
    gameController.useChip(column);
    showWin();
    fillGridPane();
    fillQueuePane();
  }

  private void showWin() {

    Winner winner = gameController.checkWin();

    String text = null;

    switch (winner) {
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
    BorderPane pane = new BorderPane();
    pane.setCenter(dialogVbox);
    Scene dialogScene = new Scene(pane, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  private void fillGridPane() {
    gridPane.setAlignment(Pos.CENTER);
    // Aufräumen...
    gridPane.getChildren().clear();

    gridPane.setGridLinesVisible(true);
    Grid grid = gameController.getGridController().getGrid();

    for (int colNumber = 0; colNumber < grid.getChips().size(); colNumber++) {
      LinkedList<ChipView> column = grid.getChips().get(colNumber);
      int gridRowNumber = 0;
      for (int rowNumber = column.size() - 1; rowNumber >= 0; rowNumber--) {
        ChipView chipView = column.get(rowNumber);
        int finalColNumber = colNumber;
        chipView.setOnMouseClicked(mouseEvent -> clickUseChip(finalColNumber));
        gridPane.add(chipView, colNumber, gridRowNumber++);
      }
    }
  }

  private void fillQueuePane() {
    BorderPane.setMargin(chipQueuePane, new Insets(5, 5, 5, 5));
    BorderPane.setAlignment(chipQueuePane, Pos.CENTER);
    chipQueuePane.setAlignment(Pos.CENTER);
    // Aufräumen
    chipQueuePane.getChildren().clear();

    Player player = gameController.getActivePlayer();

    int colCount = 0;
    // Zukünftige Chips anfügen
    for (Chip chip : player.getChipQueue().getNextChips()) {
      ChipView chipView = new ChipView(-1, -1);
      chipView.setOpacity(.5);
      chipView.setChip(chip);
      chipQueuePane.add(chipView, colCount, 0);
      colCount++;
    }

    // Verwendbare Chips anfügen
    for (Chip chip : player.getChipQueue().getUsableChips()) {
      final SelectableChipView chipView = new SelectableChipView();

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
      if (node instanceof SelectableChipView) {
        ((SelectableChipView) node).setUnselected();
      }
    });
  }
}