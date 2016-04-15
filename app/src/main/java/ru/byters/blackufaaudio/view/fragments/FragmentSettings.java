package ru.byters.blackufaaudio.view.fragments;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.controllers.ControllerSettings;
import ru.byters.blackufaaudio.controllers.Core;
import ru.byters.blackufaaudio.view.adapters.SettingSoundsAdapter;

public class FragmentSettings extends FragmentBase
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static FragmentSettings getInstance() {
        return new FragmentSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        v.findViewById(R.id.tvFeedback).setOnClickListener(this);
        v.findViewById(R.id.tvByters).setOnClickListener(this);
        v.findViewById(R.id.tvVisitChannel).setOnClickListener(this);

        SwitchCompat sc = (SwitchCompat) v.findViewById(R.id.scStartupMode);
        if (ControllerSettings.getInstance().isDisplaySettingsOnStartup(getContext()))
            sc.setChecked(true);
        else sc.setChecked(false);
        sc.setOnCheckedChangeListener(this);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rvSounds);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new SettingSoundsAdapter((Application) getContext().getApplicationContext()));

        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ControllerSettings.getInstance().setDisplaySettingsOnStartup(getContext(), isChecked);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvFeedback:
                ((Core) getContext().getApplicationContext()).sendFeedback(getContext());
                break;
            case R.id.tvByters:
                ((Core) getContext().getApplicationContext()).navigateWebsite(getContext());
                break;
            case R.id.tvVisitChannel:
                ((Core) getContext().getApplicationContext()).navigateYouTube(getContext());
                break;
        }
    }
}
