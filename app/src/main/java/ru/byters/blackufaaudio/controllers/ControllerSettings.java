package ru.byters.blackufaaudio.controllers;

import android.content.Context;

public class ControllerSettings {
    private static ControllerSettings instance;

    public static ControllerSettings getInstance() {
        if (instance == null)
            instance = new ControllerSettings();
        return instance;
    }

    public boolean isDisplaySettingsOnStartup(Context context) {
        //todo implement
        return false;
    }

    public void setDisplaySettingsOnStartup(Context context, boolean isChecked) {
        //todo implement
    }
}
