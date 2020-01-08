package br.com.yourapp.videosprivadosrunner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.yourapp.videosprivados.VideoPrivadoPlayer;
import br.com.yourapp.videosprivados.VideosPrivadosHelper;
import br.com.yourapp.videosprivados.interfaces.OnVideoListLoaded;
import br.com.yourapp.videosprivados.interfaces.OnVideoLoaded;
import br.com.yourapp.videosprivados.models.Video;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Examples how to use the VideoPrivados Framework
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.videoPlayer)
    VideoPrivadoPlayer videoPlayer;

    @BindView(R.id.videosList)
    RecyclerView videosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        VideosPrivadosHelper.getInstance().initialize("HENS3432n432h3b2u384234b203042348jdsknhd", this);

        /**
         * You can use this method to get a specific video by token from the site
         */
        /*VideosPrivadosHelper.getInstance().getVideoByID(1, new OnVideoLoaded() {
            @Override
            public void onLoad(Video video) {
                videoPlayer.playVideo(video);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });*/

        /**
         * You can use this method to obtain a list of videos passing the pagination and the quantity
         */
        VideosPrivadosHelper.getInstance().getVideosListByPage(1, 10, new OnVideoListLoaded() {
            @Override
            public void onLoad(ArrayList<Video> videos) {
                VideosListAdapter adapter = new VideosListAdapter(videos);
                videosList.setAdapter(adapter);
                videosList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        videoPlayer.setTitle("Video Title");
        videoPlayer.hideTitle(1000);
    }
}
