package de.gamejam.control;

import java.util.LinkedList;
import java.util.List;

import de.gamejam.model.Player;

public class GameController {

  private final QueueController queueController;
  private final FieldController fieldController;
  private final List<Player> players;


  public GameController() {
    queueController = new QueueController();
    fieldController = new FieldController();
    players = new LinkedList<>();
  }

  public void createPlayer(String name){
    Player player = new Player(name);

    // Die ChipQueue des Spieler auffüllen
    queueController.initQueue(player.getChipQueue());

    // Den Spieler zum Spiel hinzufügen
    players.add(player);
  }
}
