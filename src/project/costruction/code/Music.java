package project.costruction.code;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class Music implements Runnable {

    private Clip clip;
    Vector<String> musicOut = new Vector<>();
    Vector<String> musics = new Vector<>();
    AudioInputStream ais;
    public boolean isPaused()
    {
        return !clip.isRunning();
    }
    public void pausePlay() {
        if (clip.isRunning())
            clip.stop();
        else
            clip.start();
    }

    public void nextMusic() {
        clip.close();
        if (musics.isEmpty()) {
            musics.addAll(musicOut);
            musicOut.clear();
        }
        Collections.shuffle(musics);
        String tempMusic;
        tempMusic = musics.firstElement();
        musics.remove(tempMusic);
        musicOut.add(tempMusic);
        File soundFile = new File("music/" + tempMusic); //Звуковой файл
        try {
            //Получаем AudioInputStream
            //Вот тут могут полететь IOException и UnsupportedAudioFileException
            ais = AudioSystem.getAudioInputStream(soundFile);

            //Получаем реализацию интерфейса Clip
            //Может выкинуть LineUnavailableException
            clip = AudioSystem.getClip();

            //Загружаем наш звуковой поток в Clip
            //Может выкинуть IOException и LineUnavailableException
            clip.open(ais);

            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 1; i < 8; i++) {
            musics.add("music" + i + ".wav");
        }
        Collections.shuffle(musics);
        String tempMusic = musics.firstElement();
        musics.remove(tempMusic);
        musicOut.add(tempMusic);
        while (true) {
            try {
                File soundFile = new File("music/" + tempMusic); //Звуковой файл

                //Получаем AudioInputStream
                //Вот тут могут полететь IOException и UnsupportedAudioFileException
                ais = AudioSystem.getAudioInputStream(soundFile);

                //Получаем реализацию интерфейса Clip
                //Может выкинуть LineUnavailableException
                clip = AudioSystem.getClip();

                //Загружаем наш звуковой поток в Clip
                //Может выкинуть IOException и LineUnavailableException
                clip.open(ais);

                clip.setFramePosition(0); //устанавливаем указатель на старт
                clip.start(); //Поехали!!!

                //Если не запущено других потоков, то стоит подождать, пока клип не закончится
                //В GUI-приложениях следующие 3 строчки не понадобятся
                Thread.sleep(clip.getMicrosecondLength() / 1000);
                clip.stop(); //Останавливаем
                clip.close(); //Закрываем
                nextMusic();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException exc) {
                exc.printStackTrace();
            }
        }
    }
}