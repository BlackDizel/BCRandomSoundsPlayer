package ru.byters.blackufaaudio.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import ru.byters.blackufaaudio.view.adapters.FavoritedSongsAdapter;

public class ControllerFavorited extends ControllerBase {
    private static ControllerFavorited instance;

    private ArrayList<String> data;

    public static ControllerFavorited getInstance() {
        if (instance == null) instance = new ControllerFavorited();
        return instance;
    }


    public boolean isFavorited(Context context, String filename) {
        return getData(context).contains(filename);
    }

    @NonNull
    private ArrayList<String> getData(@Nullable Context context) {
        if (data == null && context != null)
            data = (ArrayList<String>) ControllerStorage.readObjectFromFile(context, ControllerStorage.CACHE_FAVORITED);
        if (data == null) data = new ArrayList<>();
        return data;
    }

    public void switchFav(Context context, String filename) {
        if (isFavorited(context, filename))
            getData(context).remove(filename);
        else
            getData(context).add(filename);
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.CACHE_FAVORITED);
        FavoritedSongsAdapter.getInstance(context).updateData();
    }

    public int getDataSize(Context context) {
        return getData(context).size();
    }
}
