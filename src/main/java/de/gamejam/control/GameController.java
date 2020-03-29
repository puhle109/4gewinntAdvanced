package de.gamejam.control;

import java.util.LinkedList;

import de.gamejam.helper.SoundMachine;
import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.Player;
import de.gamejam.model.Winner;
import de.gamejam.model.ui_element.ChipView;

public class GameController {

  private final QueueController queueController;
  private final GridController gridController;
  private final LinkedList<Player> players;
  private static final int ROWCOUNT  = 7;
  private static final int COLCOUNT  = 9;

  public GameController() {
    queueController = new QueueController();
    gridController = new GridController(ROWCOUNT, COLCOUNT);
    players = new LinkedList<>();
  }

  public void resetGame(){
    getGridController().getGrid().init(ROWCOUNT, COLCOUNT);
    for (Player player:players) {
      queueController.initQueue(player.getChipQueue(), player.getColor());
    }
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

  public void changePlayer(){
    // Der vorderste Spieler wird hinten angehangen
    players.addLast(players.removeFirst());
  }

  public Player getActivePlayer() {
    return players.getFirst();
  }

  public ChipView useChip(int column) {
    Player activePlayer = getActivePlayer();

    if (activePlayer.getChoosenChip() == null){
      return null;
    }

    Chip chip = activePlayer.getChoosenChip().getChip();
    activePlayer.setChoosenChip(null);
    ChipView chipView = gridController.getGrid().addChip(chip, column);

    queueController.useChip(activePlayer.getChipQueue(), chip);

    return chipView;
  }

  public void useSpecial(ChipView chipView){
    gridController.useSpecial(chipView);
  }

  public void gravity(){
    gridController.gravity();
  }

  public Winner checkWin(){
    return gridController.checkWin();
  }
}
