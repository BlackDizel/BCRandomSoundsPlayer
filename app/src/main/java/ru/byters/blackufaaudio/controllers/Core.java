package ru.byters.blackufaaudio.controllers;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import ru.byters.blackufaaudio.R;

public class Core extends Application {

    public void sendFeedback(Context context) {
        String uri = getString(R.string.feedback_form_address);
        navigateUri(context, uri);
    }

    public void navigateWebsite(Context context) {
        String uri = getString(R.string.website_address);
        navigateUri(context, uri);
    }

    private void navigateUri(Context context, String uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (intent.resolveActivity(getPackageManager()) == null)
            return;
        context.startActivity(intent);
    }

    public void navigateYouTube(Context context) {
        String uri = getString(R.string.website_address_channel);
        navigateUri(context, uri);
    }

    public void navigateTwitch(Context context) {
        String uri = getString(R.string.website_address_twitch);
        navigateUri(context, uri);
    }

    public void navigateArchive(Context context) {
        String uri = getString(R.string.website_address_archive);
        navigateUri(context, uri);
    }

    public void navigateVK(Context context) {
        String uri = getString(R.string.website_address_vk);
        navigateUri(context, uri);
    }

    public void navigateVKByters(Context context) {
        String uri = getString(R.string.website_address_byters_vk);
        navigateUri(context, uri);
    }

}
