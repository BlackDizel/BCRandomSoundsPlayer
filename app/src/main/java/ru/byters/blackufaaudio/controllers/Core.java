package ru.byters.blackufaaudio.controllers;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import ru.byters.blackufaaudio.R;

public class Core extends Application {

    public static void sendFeedback(Context context) {
        String uri = context.getString(R.string.feedback_form_address);
        navigateUri(context, uri);
    }

    public static void navigateWebsite(Context context) {
        String uri = context.getString(R.string.website_address);
        navigateUri(context, uri);
    }

    public static void navigateUri(Context context, String uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (intent.resolveActivity(context.getPackageManager()) == null)
            return;
        context.startActivity(intent);
    }

    public static void navigateYouTube(Context context) {
        String uri = context.getString(R.string.website_address_channel);
        navigateUri(context, uri);
    }

    public static void navigateTwitch(Context context) {
        String uri = context.getString(R.string.website_address_twitch);
        navigateUri(context, uri);
    }

    public static void navigateArchive(Context context) {
        String uri = context.getString(R.string.website_address_archive);
        navigateUri(context, uri);
    }

    public static void navigateVK(Context context) {
        String uri = context.getString(R.string.website_address_vk);
        navigateUri(context, uri);
    }

    public static void navigateVKByters(Context context) {
        String uri = context.getString(R.string.website_address_byters_vk);
        navigateUri(context, uri);
    }

}
