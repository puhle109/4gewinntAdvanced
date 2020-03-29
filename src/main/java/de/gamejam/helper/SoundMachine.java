package de.gamejam.helper;

import de.gamejam.model.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundMachine {

  public void playSound(Sound sound) {
    if (Sound.NONE.equals(sound)) {
      return;
    }
    Media hit = new Media(getClass().getResource("/sound/" + sound.getFilename()).toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(hit);
    mediaPlayer.setVolume(1.0);
    mediaPlayer.play();
  }
}
