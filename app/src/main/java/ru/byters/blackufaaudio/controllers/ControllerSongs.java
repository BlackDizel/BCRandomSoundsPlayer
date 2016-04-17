package ru.byters.blackufaaudio.controllers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import ru.byters.blackufaaudio.model.SoundData;

public class ControllerSongs extends ControllerBase implements MediaPlayer.OnPreparedListener {

    //META FILE FORMAT: <filename>|<TITLE>|<SHORT TITLE>|<SOURCE_TITLE>|<SOURCE_URL>|<author>\n
    private static final String ASSET_META_FILE = "meta.txt";
    private static final String META_DELIMETER_PATTERN = "[|]";
    private static ControllerSongs instance;
    private MediaPlayer mp;
    private Random r;
    private ArrayList<SoundData> data;

    private ControllerSongs() {
        r = new Random();
    }

    public static ControllerSongs getInstance() {
        if (instance == null)
            instance = new ControllerSongs();
        return instance;
    }

    public void playRandom(@NonNull Context context) {
        if (getSoundsSize(context) == 0) return;
        int pos = r.nextInt(getData(context).size());
        playSong(context, getFilename(context, pos));
    }

    public void playSong(@NonNull Context context, String filename) {
        if (TextUtils.isEmpty(filename)) return;

        if (mp == null) {
            mp = new MediaPlayer();
            mp.setOnPreparedListener(this);
        } else {
            if (mp.isPlaying()) return;
            mp.reset();
        }

        try {
            AssetFileDescriptor afd = context.getAssets().openFd(filename);
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
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

    public int getSoundsSize(Context context) {
        return getData(context) == null ? 0 : getData(context).size();
    }

    @Nullable
    public String getFilename(Context context, int position) {
        if (getSoundsSize(context) == 0)
            return null;
        if (position < 0 || position >= getData(context).size()) return null;

        SoundData item = getData(context).get(position);
        if (item == null) return null;
        return item.getFilename();
    }

    @Nullable
    public String getItemTitle(Context context, String filename) {
        SoundData item = getItem(context, filename);
        if (item == null) return null;
        return item.getTitle();
    }

    private ArrayList<SoundData> getData(Context context) {
        if (data != null) return data;

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(ASSET_META_FILE)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(META_DELIMETER_PATTERN);
                if (arr == null || arr.length == 0)
                    continue;
                if (data == null) data = new ArrayList<>();
                SoundData item = new SoundData(arr[0]
                        , arr.length > 1 ? arr[1] : null
                        , arr.length > 2 ? arr[2] : null
                        , arr.length > 3 ? arr[3] : null
                        , arr.length > 4 ? arr[4] : null
                        , arr.length > 5 ? arr[5] : null);
                data.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Nullable
    public String getItemTitleShort(Context context, String filename) {
        SoundData item = getItem(context, filename);
        if (item == null) return null;
        return item.getShortTitle();
    }

    private SoundData getItem(Context context, String filename) {
        if (getSoundsSize(context) == 0) return null;
        for (SoundData item : getData(context)) {
            if (item == null)
                continue;
            if (item.getFilename().equals(filename))
                return item;
        }
        return null;
    }

    @Nullable
    public String getItemSourceTitle(Context context, String filename) {
        SoundData item = getItem(context, filename);
        if (item == null) return null;
        return item.getSourceTitle();
    }

    @Nullable
    public String getItemSourceUrl(Context context, String filename) {
        SoundData item = getItem(context, filename);
        if (item == null) return null;
        return item.getUrl();
    }

    @Nullable
    public String getItemAuthor(Context context, String filename) {
        SoundData item = getItem(context, filename);
        if (item == null) return null;
        return item.getAuthor();
    }
}
