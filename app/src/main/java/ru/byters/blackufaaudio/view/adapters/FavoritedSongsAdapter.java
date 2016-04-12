package ru.byters.blackufaaudio.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.controllers.ControllerFavorited;
import ru.byters.blackufaaudio.controllers.ControllerSongs;
import ru.byters.blackufaaudio.controllers.Core;

public class FavoritedSongsAdapter extends RecyclerView.Adapter<FavoritedSongsAdapter.ViewHolder> {
    private static FavoritedSongsAdapter instance;
    private Core core;

    public static FavoritedSongsAdapter getInstance(Context context) {
        if (instance == null) instance = new FavoritedSongsAdapter();
        instance.setCore(context);
        return instance;
    }

    private void setCore(Context context) {
        core = (Core) context.getApplicationContext();
    }

    public void updateData() {
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_favorited_song, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return ControllerFavorited.getInstance().getDataSize(core);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private int id;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(int position) {
            //todo implement
            id = ControllerSongs.getInstance().getItemId(position);
        }

        @Override
        public void onClick(View v) {
            if (id == ControllerSongs.NO_VALUE)
                return;
            ControllerSongs.getInstance().playSong(v.getContext(), id);
        }
    }
}