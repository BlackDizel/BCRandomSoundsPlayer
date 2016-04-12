package ru.byters.blackufaaudio.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.controllers.ControllerSongs;

public class SettingSoundsAdapter extends RecyclerView.Adapter<SettingSoundsAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_settings_sound_item
                                , parent
                                , false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return ControllerSongs.getInstance().getSoundsSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        private TextView tvTitle;
        private View vFav;
        private int id;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            vFav = itemView.findViewById(R.id.vFavorited);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        private void resetData() {
            tvTitle.setText(R.string.settings_sounds_item_title_error);
        }

        public void setData(int position) {
            resetData();
            this.id = ControllerSongs.getInstance().getItemId(position);
            if (id == ControllerSongs.NO_VALUE)
                return;

            checkFav();

            String s = ControllerSongs.getInstance().getItemTitle(id);
            if (!TextUtils.isEmpty(s))
                tvTitle.setText(s);
        }

        private void checkFav() {
            if (ControllerSongs.getInstance().isFavorited(id))
                vFav.setVisibility(View.VISIBLE);
            else
                vFav.setVisibility(View.GONE);
        }


        @Override
        public void onClick(View v) {
            if (id == ControllerSongs.NO_VALUE)
                return;
            ControllerSongs.getInstance().playSong(id);
        }

        @Override
        public boolean onLongClick(View v) {
            if (id == ControllerSongs.NO_VALUE)
                return true;
            ControllerSongs.getInstance().switchFav(id);
            checkFav();
            return true;
        }
    }
}
