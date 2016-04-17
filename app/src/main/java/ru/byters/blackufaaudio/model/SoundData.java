package ru.byters.blackufaaudio.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SoundData {
    private String filename;
    private String title;
    private String shortTitle;

    private String sourceTitle;
    private String url;
    private String author;

    public SoundData(@NonNull String filename
            , @Nullable String title
            , @Nullable String shortTitle
            , @Nullable String sourceTitle
            , @Nullable String url
            , @Nullable String author
    ) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.filename = filename;
        this.sourceTitle = sourceTitle;
        this.url = url;
        this.author = author;
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

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getSourceTitle() {
        return sourceTitle;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }
}
