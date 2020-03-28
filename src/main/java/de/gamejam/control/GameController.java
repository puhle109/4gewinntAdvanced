package de.gamejam.control;

import java.util.LinkedList;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.Player;
import de.gamejam.model.Winner;

public class GameController {

  private final QueueController queueController;
  private final GridController gridController;
  private final LinkedList<Player> players;


  public GameController() {
    queueController = new QueueController();
    gridController = new GridController();
    players = new LinkedList<>();
  }

  public void createPlayer(ChipColor chipColor){
    Player player = new Player(chipColor);

    // Die ChipQueue des Spieler auffüllen
    queueController.initQueue(player.getChipQueue(), player.getColor());

    // Den Spieler zum Spiel hinzufügen
    players.add(player);
  }

  public QueueController getQueueController() {
    return queueController;
  }

  public GridController getGridController() {
    return gridController;
  }

  private void changePlayer(){
    // Der vorderste Spieler wird hinten angehangen
    players.addLast(players.removeFirst());
  }

  public Player getActivePlayer() {
    return players.getFirst();
  }

  public void useChip(int column) {
    Player activePlayer = getActivePlayer();

    if (activePlayer.getChoosenChip() == null){
      return;
    }

    Chip chip = activePlayer.getChoosenChip().getChip();
    gridController.getGrid().addChip(chip, column);
    queueController.useChip(activePlayer.getChipQueue(), chip);
    changePlayer();
  }

  public Winner checkWin(){
    return gridController.checkWin();
  }
}
