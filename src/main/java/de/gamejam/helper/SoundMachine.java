package de.gamejam.helper;

import java.util.HashMap;

import de.gamejam.model.Sound;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;

public class SoundMachine {

  private HashMap<Sound, AudioClip> soundCache = new HashMap<>();

  public void playSound(Sound sound) {
    if (Sound.NONE.equals(sound)) {
      return;
    }

    if (!soundCache.containsKey(sound)) {
      soundCache.put(sound, new AudioClip(getClass().getResource("/sound/" + sound.getFilename()).toExternalForm()));
    }
    final AudioClip audioClip = soundCache.get(sound);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        audioClip.play(100);
        System.out.println("hi");
      }
    });
  }
}
