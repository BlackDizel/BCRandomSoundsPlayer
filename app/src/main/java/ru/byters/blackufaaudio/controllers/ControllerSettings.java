package ru.byters.blackufaaudio.controllers;

import android.content.Context;

public class ControllerSettings extends ControllerBase {
    private static ControllerSettings instance;

    public static ControllerSettings getInstance() {
        if (instance == null)
            instance = new ControllerSettings();
        return instance;
    }

    public boolean isDisplaySettingsOnStartup(Context context) {
        return ControllerStorage.getPreferences(context).getBoolean(ControllerStorage.PREF_STARTUP_DISPLAY_ALL, false);
    }

    public void setDisplaySettingsOnStartup(Context context, boolean isChecked) {
        ControllerStorage.getPreferences(context)
                .edit()
                .putBoolean(ControllerStorage.PREF_STARTUP_DISPLAY_ALL, isChecked)
                .apply();
    }
}
