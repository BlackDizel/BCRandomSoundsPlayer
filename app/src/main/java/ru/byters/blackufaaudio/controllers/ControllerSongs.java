package ru.byters.blackufaaudio.controllers;

public class ControllerSongs extends ControllerBase {
    private static ControllerSongs instance;

    public static ControllerSongs getInstance() {
        if (instance == null)
            instance = new ControllerSongs();
        return instance;
    }

    public void playRandom() {
        //todo implement
    }
}
