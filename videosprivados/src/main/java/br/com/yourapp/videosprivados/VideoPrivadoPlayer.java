package br.com.yourapp.videosprivados;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import br.com.yourapp.videosprivados.models.Video;
import br.com.yourapp.videosprivados.utils.Constants;

public class VideoPrivadoPlayer extends RelativeLayout {

    View rootView;
    private VideoView videoView;
    private static final String TAG = "VideoPrivadoPlayer";

    private Context context;
    private TextView txtTitle;
    private RelativeLayout contentTitle;
    private String apiToken;

    public VideoPrivadoPlayer(Context context) {
        super(context);
        init(context, null);
    }

    public VideoPrivadoPlayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VideoPrivadoPlayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rootView = inflate(context, R.layout.videoplayer, this);
        videoView = rootView.findViewById(R.id.video);
        txtTitle = rootView.findViewById(R.id.txtTitle);
        contentTitle = rootView.findViewById(R.id.contentTitle);

        this.context = context;

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.VideoPrivadoAttrs);
        String customURL = attributes.getString(R.styleable.VideoPrivadoAttrs_video_token);
        String customTitle = attributes.getString(R.styleable.VideoPrivadoAttrs_custom_title);
        apiToken = attributes.getString(R.styleable.VideoPrivadoAttrs_api_token);

        if(customURL != null) {
            if(apiToken != null) {
                playVideo(customURL);
            } else {
                System.err.println("To use embedded video token, you need to set the app:api_token too!");
            }
        }

        if(customTitle != null) {
            txtTitle.setText(customTitle);
        }
    }

    public void playVideo(Video video) {
        Uri uri = Uri.parse(Constants.VIDEO_URL + video.token + "&apikey=" + VideosPrivadosHelper.getInstance().getApiKey());

        MediaController mediaController = new MediaController(this.context);
        videoView.setMediaController(mediaController);
        videoView.setKeepScreenOn(true);

        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(mediaPlayer -> videoView.start());
    }

    /**
     * Embeded video on XML
     * Also you need to sed the attribute app:api_token
     *
     * @param token
     */
    public void playVideo(String token) {
        Uri uri = Uri.parse(Constants.VIDEO_URL + token + "&apikey=" + apiToken);

        MediaController mediaController = new MediaController(this.context);
        videoView.setMediaController(mediaController);
        videoView.setKeepScreenOn(true);

        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(mediaPlayer -> videoView.start());
    }

    public void hideTitle() {
        contentTitle.animate().alpha(0f).setDuration(1000).start();
    }

    public void hideTitle(int afterSeconds) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> contentTitle.animate().alpha(0f).setDuration(1000).start(), afterSeconds);
    }

    public void showTitle() {
        contentTitle.animate().alpha(1f).setDuration(1000).start();
    }

    public void setTitle(String title) {
        txtTitle.setText(title);
    }
}
