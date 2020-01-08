package br.com.yourapp.videosprivadosrunner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import br.com.yourapp.videosprivados.models.Video;

public class VideosListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "TicketsListAdapter";

    ArrayList<Video> videos;

    public VideosListAdapter(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_video, parent, false);
        RecyclerView.ViewHolder viewHolder = new VideosListAdapter.VideoHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        public VideoHolder(View itemView) {
            super(itemView);
        }
    }
}
