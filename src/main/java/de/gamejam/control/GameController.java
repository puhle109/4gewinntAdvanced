package de.gamejam.control;

import java.util.List;

import de.gamejam.model.Player;

public class GameController {

  private QueueController queueController = new QueueController();

  private List<Player> players;

  public void createPlayer(String name){
    Player player = new Player(name);

    // Die ChipQueue des Spieler auffüllen
    queueController.initQueue(player.getChipQueue());
  }
}
