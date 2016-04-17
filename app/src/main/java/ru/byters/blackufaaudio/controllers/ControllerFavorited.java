package ru.byters.blackufaaudio.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;

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
        Collections.sort(data);
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

    @Nullable
    public String getFilename(Context context, int position) {
        if (getData(context) == null || getData(context).size() == 0) return null;
        if (position < 0 || position >= getData(context).size())
            return null;
        return getData(context).get(position);
    }
}
