package ru.byters.blackufaaudio.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SoundData {
    private String title;
    private String shortTitle;
    private String filename;

    public SoundData(@NonNull String filename
            , @Nullable String title
            , @Nullable String shortTitle) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.filename = filename;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getShortTitle() {
        return shortTitle;
    }

    public String getFilename() {
        return filename;
    }
}
