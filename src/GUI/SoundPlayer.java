package GUI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundPlayer {
	
	public SoundPlayer() {
	
	}
	
	public void playNo() {
		int random = 1 + (int)(Math.random() * 3);
		
		if(random == 1) {
			String musicFile = "src/sounds/no1.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}else if (random == 2) {
			String musicFile = "src/sounds/no2.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}else {
			String musicFile = "src/sounds/no3.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}
	}
}
