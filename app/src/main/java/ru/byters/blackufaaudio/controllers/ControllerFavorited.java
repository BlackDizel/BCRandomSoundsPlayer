package ru.byters.blackufaaudio.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public class ControllerFavorited extends ControllerBase {
    private static ControllerFavorited instance;

    private ArrayList<Integer> data;

    public static ControllerFavorited getInstance() {
        if (instance == null) instance = new ControllerFavorited();
        return instance;
    }


    public boolean isFavorited(Context context, int id) {
        return getData(context).contains(id);
    }

    @NonNull
    private ArrayList<Integer> getData(Context context) {
        if (data == null)
            data = (ArrayList<Integer>) ControllerStorage.readObjectFromFile(context, ControllerStorage.CACHE_FAVORITED);
        if (data == null) data = new ArrayList<>();
        return data;
    }

    public void switchFav(Context context, int id) {
        if (isFavorited(context, id))
            getData(context).remove((Integer) id);
        else
            getData(context).add(id);
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.CACHE_FAVORITED);
    }
}
