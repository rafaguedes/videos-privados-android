package br.com.yourapp.videosprivados.di.api;

import java.util.ArrayList;

import br.com.yourapp.videosprivados.models.Video;
import br.com.yourapp.videosprivados.models.VideosList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VideosPrivadosAPI {
    @POST("api/get-video-by-id")
    @FormUrlEncoded
    Call<Video> getVideoByID(@Field("idVideo") int idVideo, @Field("apiKey") String apiKey);

    @POST("api/get-videos-list-by-page")
    @FormUrlEncoded
    Call<VideosList> getVideosListByPage(@Field("page") int page, @Field("quantityPerPage") int quantityPerPage, @Field("apiKey") String apiKey);
}

