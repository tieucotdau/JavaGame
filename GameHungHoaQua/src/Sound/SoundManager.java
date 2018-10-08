package Sound;

public class SoundManager {
	private SourceEffect soundBackground;

	public SoundManager() {
		soundBackground = new SourceEffect("song.wav");

	}

	public SourceEffect getSoundBackground() {
		return soundBackground;
	}

	public SourceEffect getSoundFruit() {
		return new SourceEffect("fruit.wav");
	}

	public SourceEffect getSoundSlide() {
		return new SourceEffect("wrong.wav");
	}

	public SourceEffect getSoundCloud() {
		return new SourceEffect("cloud.wav");
	}

	public SourceEffect getSoundMouseEntered() {
		return new SourceEffect("MouseEntered.wav");
	}

	public SourceEffect getSoundBomb() {
		return new SourceEffect("Bomb.wav");
	}
}
