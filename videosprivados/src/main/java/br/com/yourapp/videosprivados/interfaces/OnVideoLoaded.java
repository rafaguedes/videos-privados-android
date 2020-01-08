package br.com.yourapp.videosprivados.interfaces;

import br.com.yourapp.videosprivados.models.Video;

public interface OnVideoLoaded {
    void onLoad(Video video);
    void onError(String message);
}
