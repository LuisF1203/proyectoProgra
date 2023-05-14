package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    public static Clip backgroundClip;
    public static File backgroundClipFile;
    public static File jumpClipFile;
    public static File gameOverClipFile;

    static void initializeBackgroundAudio() {
        System.out.println(AudioManager.backgroundClipFile);
        try {
            File audioFile = AudioManager.backgroundClipFile;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioManager.backgroundClip = AudioSystem.getClip();
            AudioManager.backgroundClip.open(audioStream);
            AudioManager.backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    // Método para detener el audio de fondo
    public static void stopBackgroundAudio() {
        try {
            // Obtiene información de todos los mezcladores de audio
            Mixer.Info[] mixers = AudioSystem.getMixerInfo();

            // Recorre todos los mezcladores de audio y los cierra
            for (Mixer.Info mixerInfo : mixers) {
                Mixer mixer = AudioSystem.getMixer(mixerInfo);
                mixer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
