package br.com.yourapp.videosprivados.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import br.com.yourapp.videosprivados.di.api.VideosPrivadosAPI;
import br.com.yourapp.videosprivados.utils.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {OkHttpClientModule.class, ContextModule.class})
public class RetrofitModule {

    private static final String TAG = "RetrofitModule";

    @Provides
    public VideosPrivadosAPI videosPrivadosAPI(Retrofit retrofit){
        return retrofit.create(VideosPrivadosAPI.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.API_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder().setLenient();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
