package br.com.yourapp.videosprivados.di.component;

import br.com.yourapp.videosprivados.di.api.VideosPrivadosAPI;
import br.com.yourapp.videosprivados.di.modules.RetrofitModule;
import dagger.Component;

@Component(modules = {RetrofitModule.class})
public interface VideosPrivadosComponent {
    VideosPrivadosAPI getService();
}
