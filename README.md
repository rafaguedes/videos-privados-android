# Videos Privados Android SDK
Plataforma para disponibilizar seus vídeos privados(conteúdo premium) dentro de seus apps Android e iOS ou seu site via jQuery.<br />
<br />
Porque utilizar os VídeosPrivados e não o Youtube?<br />
Infelizmente o YouTube não disponbiliza uma forma transparente ao desenvolvedor/youtuber para cobrar pelos seus vídeos privados.<br />
Portanto, se você deseja criar conteúdo premium para seus seguidores, esta é sua plataforma!<br />
<br />
Porque utilzar os VídeosPrivados e não o Vimeo?<br />
A Vimeo disponiliza uma plataforma completa tanto em Android quanto em iOS para publicar seu conteudo premium, entretanto, o seu custo é muito alto. <br />
Este é nosso diferencial, temos uma plataforma similar a Vimeo, com valor mais em conta!<br />
<br />

## Como utilizar
<br />
Adicione o repositório correto

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Adicione a dependência

```gradle
dependencies {
    implementation 'com.github.rafaguedes:videos-privados-android:1.0.0'
}
```

Adicione as permissões
```xml
<manifest>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
</manifest>
```

## Veja como é simples!
<br />

Primeiramente você deve inicializar a SDK.
```java
VideosPrivadosHelper.getInstance().initialize("YOUR_API_TOKEN", this);
```

Depois você pode adicionar via XML ou programaticamente!</br>
Veja como:

## Basic Configuration
```xml
<br.com.yourapp.videosprivados.VideoPrivadoPlayer
    android:id="@+id/videoPlayer"
    android:layout_width="match_parent"
    app:video_token="YOUR_VIDEO_TOKEN"
    app:api_token="YOUR_API_TOKEN"
    app:custom_title="Titulo do Video"
    android:layout_height="match_parent"/>
```

## Via Código
Obtendo um objeto Video pela seu token
```java
VideoPrivadoPlayer videoPlayer;
VideosPrivadosHelper.getInstance().getVideoByID("VIDEO_TOKEN", new OnVideoLoaded() {
    @Override
    public void onLoad(Video video) {
        videoPlayer.playVideo(video);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }
});
```

Obtendo uma lista de vídeos
```java
RecyclerView videosList;
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
```
