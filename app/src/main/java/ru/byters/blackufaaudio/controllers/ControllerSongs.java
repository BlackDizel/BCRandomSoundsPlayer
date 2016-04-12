package ru.byters.blackufaaudio.controllers;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

import ru.byters.blackufaaudio.BuildConfig;
import ru.byters.blackufaaudio.R;

public class ControllerSongs extends ControllerBase implements MediaPlayer.OnPreparedListener {

    private static final String RAW_RESOURCE_PATH_FORMAT = "android.resource://%s/%d";
    private static final int NO_VALUE = -1;
    private static ControllerSongs instance;
    //https://developer.android.com/reference/android/media/SoundPool.html
    private MediaPlayer mp;
    private Random r;
    private Field[] arrRawFieldsReflected;

    private ControllerSongs() {
        r = new Random();
        arrRawFieldsReflected = R.raw.class.getFields();

    }

    public static ControllerSongs getInstance() {
        if (instance == null)
            instance = new ControllerSongs();
        return instance;
    }

    public void playRandom(@NonNull Context context) {
        if (arrRawFieldsReflected == null || arrRawFieldsReflected.length == 0) return;

        if (mp == null) {
            mp = new MediaPlayer();
            mp.setOnPreparedListener(this);
        } else {
            if (mp.isPlaying()) return;
            mp.reset();
        }

        int value = NO_VALUE;
        try {
            value = (int) arrRawFieldsReflected[r.nextInt(arrRawFieldsReflected.length)].get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (value == NO_VALUE) return;

        Uri uri = Uri.parse(
                String.format(RAW_RESOURCE_PATH_FORMAT
                        , BuildConfig.APPLICATION_ID
                        , value));

        try {
            mp.setDataSource(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        mp.prepareAsync();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

}
