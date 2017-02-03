package de.sloth.system.game.bgmSystem;

import java.io.File;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class PlaySong implements IBehavior {

	Media bgm;
	MediaPlayer player;
	
	public PlaySong() {
		bgm = new Media(new File("bg/game_bgm.mp3").toURI().toString());
		player = new MediaPlayer(bgm);
	}
	
	@Override
	public void execute(GameSystem system) {
		if(player.getStatus().equals(Status.HALTED) || player.getStatus().equals(Status.STOPPED) || player.getStatus().equals(Status.READY)) {
			player.play();
		}
	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}
}
