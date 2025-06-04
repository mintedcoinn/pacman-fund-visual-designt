import greenfoot.*;

public class MusicManager {
    private static GreenfootSound backgroundMusic;
    private static boolean isMuted = false;
    private static float volume = 70;

    public static void play(String filename) {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
        backgroundMusic = new GreenfootSound(filename);
        backgroundMusic.playLoop();
        setVolume(volume);
    }

    public static void stop() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    public static void setVolume(float level) {
        volume = Math.max(0, Math.min(100, level));
        if (backgroundMusic != null) {
            backgroundMusic.setVolume((int) volume);
        }
    }

    public static void toggleMute() {
        isMuted = !isMuted;
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(isMuted ? 0 : (int) volume);
        }
    }
}