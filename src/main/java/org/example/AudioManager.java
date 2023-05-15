package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {

    /**
     * El Clip de audio de fondo del juego.
     */
    public static Clip backgroundClip;

    /**
     * El archivo de audio para el Clip de audio de fondo.
     */
    public static File backgroundClipFile;

    /**
     * El archivo de audio para el Clip de salto.
     */
    public static File jumpClipFile;

    /**
     * El archivo de audio para el Clip de fin de juego.
     */
    public static File gameOverClipFile;

    /**
     * Inicializa el audio de fondo del juego.
     */
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

    /**
     * Detiene el audio de fondo del juego.
     */
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
