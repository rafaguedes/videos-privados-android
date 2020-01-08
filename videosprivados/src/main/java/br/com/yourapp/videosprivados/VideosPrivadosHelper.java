package br.com.yourapp.videosprivados;

import android.content.Context;

import java.util.ArrayList;
import br.com.yourapp.videosprivados.di.api.VideosPrivadosAPI;
import br.com.yourapp.videosprivados.di.component.DaggerVideosPrivadosComponent;
import br.com.yourapp.videosprivados.di.modules.ContextModule;
import br.com.yourapp.videosprivados.interfaces.OnVideoListLoaded;
import br.com.yourapp.videosprivados.interfaces.OnVideoLoaded;
import br.com.yourapp.videosprivados.models.Video;
import br.com.yourapp.videosprivados.models.VideosList;
import br.com.yourapp.videosprivados.utils.VideosPrivadosException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosPrivadosHelper {

    private static final String TAG = "VideosPrivadosHelper";
    private String apiKey;
    public static VideosPrivadosHelper videosPrivadosHelper;
    public static Context context;

    private void setContext(Context context) {
        this.context = context;
    }

    public static synchronized VideosPrivadosHelper getInstance() {
        if(videosPrivadosHelper == null) {
            videosPrivadosHelper = new VideosPrivadosHelper();
        }
        return videosPrivadosHelper;
    }

    public void initialize(String apiKey, Context context) {
        this.apiKey = apiKey;
        videosPrivadosHelper.setContext(context);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void getVideoByID(int id, OnVideoLoaded onVideoLoaded) {
        if(apiKey == null) {
            try {
                throw new VideosPrivadosException("You didn't initialized your account! Use method initialize().");
            } catch (VideosPrivadosException e) {
                e.printStackTrace();
            }
        }

        VideosPrivadosAPI videosPrivadosAPI = DaggerVideosPrivadosComponent.builder().contextModule(new ContextModule(context)).build().getService();
        Call<Video> call = videosPrivadosAPI.getVideoByID(id, apiKey);
        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                Video video = response.body();
                if(video.status) {
                    onVideoLoaded.onLoad(video);
                } else {
                    onVideoLoaded.onError(video.message);
                }
            }

            @Override
            public void onFailure(Call<Video> call, Throwable throwable) {
                call.cancel();
                onVideoLoaded.onError("ParseException");
            }
        });
    }

    public void getVideosListByPage(int page, int quantityPerPage, OnVideoListLoaded onVideoListLoaded) {
        if(apiKey == null) {
            try {
                throw new VideosPrivadosException("You didn't initialized your account! Use method initialize().");
            } catch (VideosPrivadosException e) {
                e.printStackTrace();
            }
        }

        VideosPrivadosAPI videosPrivadosAPI = DaggerVideosPrivadosComponent.builder().contextModule(new ContextModule(context)).build().getService();
        Call<VideosList> call = videosPrivadosAPI.getVideosListByPage(page, quantityPerPage, apiKey);
        call.enqueue(new Callback<VideosList>() {
            @Override
            public void onResponse(Call<VideosList> call, Response<VideosList> response) {
                VideosList videosList = response.body();
                if(videosList.status) {
                    onVideoListLoaded.onLoad(videosList.videos);
                } else {
                    onVideoListLoaded.onError(videosList.message);
                }
            }

            @Override
            public void onFailure(Call<VideosList> call, Throwable throwable) {
                call.cancel();
                onVideoListLoaded.onError("ParseException");
            }
        });
    }
}
