package ru.byters.blackufaaudio.view.adapters;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.byters.blackufaaudio.R;
import ru.byters.blackufaaudio.controllers.ControllerFavorited;
import ru.byters.blackufaaudio.controllers.ControllerSongs;

public class SettingSoundsAdapter extends RecyclerView.Adapter<SettingSoundsAdapter.ViewHolder> {

    private Application context;

    public SettingSoundsAdapter(Application context) {
        this.context = context;
    }

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
        return context == null ? 0 : ControllerSongs.getInstance().getSoundsSize(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        private TextView tvTitle, tvSourceTitle, tvAuthor;
        private View vFav;
        private String filename;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvSourceTitle = (TextView) itemView.findViewById(R.id.tvSourceTitle);
            vFav = itemView.findViewById(R.id.vFavorited);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private void resetData() {
            tvTitle.setText(R.string.settings_sounds_item_title_error);
            tvAuthor.setText("");
            tvSourceTitle.setText("");
            tvAuthor.setVisibility(View.GONE);
            tvSourceTitle.setVisibility(View.GONE);
        }

        public void setData(int position) {
            resetData();
            this.filename = ControllerSongs.getInstance().getFilename(tvTitle.getContext(), position);
            if (TextUtils.isEmpty(filename))
                return;

            checkFav(tvTitle.getContext());

            String title = ControllerSongs.getInstance().getItemTitle(tvTitle.getContext(), filename);
            if (!TextUtils.isEmpty(title))
                tvTitle.setText(title);

            String sourceTitle = ControllerSongs.getInstance().getItemSourceTitle(tvTitle.getContext(), filename);
            if (!TextUtils.isEmpty(sourceTitle)) {
                tvSourceTitle.setText(sourceTitle);
                tvSourceTitle.setVisibility(View.VISIBLE);
            }

            String author = ControllerSongs.getInstance().getItemAuthor(tvTitle.getContext(), filename);
            if (!TextUtils.isEmpty(author)) {
                tvAuthor.setText(author);
                tvAuthor.setVisibility(View.VISIBLE);
            }
        }

        private void checkFav(Context context) {
            if (ControllerFavorited.getInstance().isFavorited(context, filename))
                vFav.setVisibility(View.VISIBLE);
            else
                vFav.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(filename))
                return;

            //todo implement open source url
                /*  String url = ControllerSongs.getInstance().getItemSourceUrl(v.getContext(), filename);
                    if (!TextUtils.isEmpty(url))
                        Core.navigateUri(v.getContext(), url);
                    */
            ControllerSongs.getInstance().playSong(v.getContext(), filename);
        }

        @Override
        public boolean onLongClick(View v) {
            if (TextUtils.isEmpty(filename))
                return true;
            ControllerFavorited.getInstance().switchFav(v.getContext(), filename);
            checkFav(v.getContext());
            return true;
        }
    }
}
