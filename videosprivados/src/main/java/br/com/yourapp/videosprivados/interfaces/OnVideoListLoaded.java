package br.com.yourapp.videosprivados.interfaces;

import java.util.ArrayList;

import br.com.yourapp.videosprivados.models.Video;

public interface OnVideoListLoaded {
    void onLoad(ArrayList<Video> videos);
    void onError(String message);
}
