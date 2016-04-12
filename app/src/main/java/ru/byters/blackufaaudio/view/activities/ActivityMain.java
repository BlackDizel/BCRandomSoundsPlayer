package ru.byters.blackufaaudio.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.view.fragments.FragmentMain;

public class ActivityMain extends ActivityBase {

    public static final int STATE_MAIN = 0;
    public static final int STATE_SETTINGS = 1;
    private static final String PAGE_TAG_MAIN = "FRAGMENT_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment f = getSupportFragmentManager().findFragmentByTag(PAGE_TAG_MAIN);
        if (f == null) f = FragmentMain.getInstance();
        f.setRetainInstance(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, f, PAGE_TAG_MAIN).commit();
    }

    public void setState(int state){
        //todo implement
    }
}
