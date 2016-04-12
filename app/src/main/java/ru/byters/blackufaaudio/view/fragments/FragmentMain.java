package ru.byters.blackufaaudio.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.controllers.ControllerSettings;
import ru.byters.blackufaaudio.controllers.ControllerSongs;
import ru.byters.blackufaaudio.view.activities.ActivityMain;

public class FragmentMain extends FragmentBase
        implements View.OnLongClickListener, View.OnClickListener {

    private View ivSettings;

    public static FragmentMain getInstance() {
        return new FragmentMain();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ivSettings = v.findViewById(R.id.ivSettings);

        v.setOnLongClickListener(this);
        v.findViewById(R.id.ivSettings).setOnClickListener(this);
        v.findViewById(R.id.ivPlay).setOnClickListener(this);

        if (ControllerSettings.getInstance().isDisplaySettingsOnStartup(getContext()))
            ivSettings.setVisibility(View.VISIBLE);

        return v;
    }

    @Override
    public boolean onLongClick(View v) {
        if (ivSettings.getVisibility() == View.GONE)
            ivSettings.setVisibility(View.VISIBLE);
        else ivSettings.setVisibility(View.GONE);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPlay:
                ControllerSongs.getInstance().playRandom(getActivity());
                break;
            case R.id.ivSettings:
                ((ActivityMain) getActivity()).setState(ActivityMain.STATE_SETTINGS);
                break;
        }
    }
}
