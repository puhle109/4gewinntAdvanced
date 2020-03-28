package de.gamejam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import de.gamejam.control.GameController;
import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.Grid;
import de.gamejam.model.Player;
import de.gamejam.model.ui_element.ChipView;

public class App extends Application {

    private static Scene scene;

    private GameController gameController = new GameController();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Super tolles Spiel");
        gameController.createPlayer(ChipColor.BLUE);
        gameController.createPlayer(ChipColor.RED);

        BorderPane root = new BorderPane();

        GridPane chipQueuePane = new GridPane();
        fillQueuePane(chipQueuePane);

        GridPane mainPane = new GridPane();

        GridPane inputPane = new GridPane();

        root.setCenter(mainPane);
        root.setBottom(chipQueuePane);
        root.setTop(inputPane);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void fillMainPane(GridPane mainPane){


    }

    private void fillQueuePane(GridPane queuePane){
        Player player = gameController.getActivePlayer();

        int colCount = 0;
        // Zukünftige Chips anfügen
        for (Chip chip : player.getChipQueue().getNextChips()){
            ChipView chipView = new ChipView();
            chipView.setOpacity(.5);
            chipView.setChip(chip);
            queuePane.add(chipView, 0, colCount);
            colCount++;
        }

        // Verwendbare Chips anfügen
        for (Chip chip : player.getChipQueue().getUsableChips()){
            ChipView chipView = new ChipView();
            chipView.setChip(chip);
            queuePane.add(chipView, 0, colCount);
            colCount++;
        }
    }

    public static void main(String[] args) {
        launch();
    }

}