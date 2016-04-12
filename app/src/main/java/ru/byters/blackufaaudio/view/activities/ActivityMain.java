package ru.byters.blackufaaudio.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.view.fragments.FragmentMain;
import ru.byters.blackufaaudio.view.fragments.FragmentSettings;

public class ActivityMain extends ActivityBase {

    public static final int STATE_MAIN = 0;
    public static final int STATE_SETTINGS = 1;
    private static final String PAGE_TAG_MAIN = "FRAGMENT_MAIN";
    private static final String PAGE_TAG_SETTINGS = "FRAGMENT_SETTINGS";
    private int currentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setState(STATE_MAIN);
    }

    public void setState(int state) {
        Fragment f = null;
        String tag = null;

        switch (state) {
            case STATE_MAIN:
                tag = PAGE_TAG_MAIN;
                f = getSupportFragmentManager().findFragmentByTag(tag);
                if (f == null) f = FragmentMain.getInstance();
                break;
            case STATE_SETTINGS:
                tag = PAGE_TAG_SETTINGS;
                f = getSupportFragmentManager().findFragmentByTag(tag);
                if (f == null) f = FragmentSettings.getInstance();
                break;
        }
        if (f == null || TextUtils.isEmpty(tag)) return;

        f.setRetainInstance(true);
        //todo add animation
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, f, tag).commit();
        currentState = state;
    }


    @Override
    public void onBackPressed() {
        if (currentState == STATE_SETTINGS)
            setState(STATE_MAIN);
        else
            super.onBackPressed();
    }
}
