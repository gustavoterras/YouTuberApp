package br.com.infoterras.youtuberapp.network;

import com.google.gson.JsonObject;

import java.util.Locale;

import br.com.infoterras.youtuberapp.BuildConfig;
import br.com.infoterras.youtuberapp.model.YouTubeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gustavoterras on 26/03/17.
 */

@SuppressWarnings("unchecked")
public class ConsumerService {

    private OnTaskCompleted listener;
    private IConsumerService service;

    public interface OnTaskCompleted<T> {

        void onSuccess(T response,int code, int requestCode);

        void onFailure(Throwable error, int requestCode);
    }

    public ConsumerService() {
        service = ServiceGenerator.createService(BuildConfig.DEBUG, BuildConfig.HTTP_URL, IConsumerService.class);
    }

    public void setOnTaskCompleted(OnTaskCompleted onTaskCompleted) {
        listener = onTaskCompleted;
    }

    public void getContent(String channelId, final int requestCode) {
        String url = String.format(Locale.getDefault(), "/youtube/v3/search?key=%s&channelId=%s&part=snippet,id&order=date&maxResults=50", BuildConfig.KEY, channelId);
        service.getContent(url).enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    public void getComment(String videoId, final int requestCode) {
        String url = String.format(Locale.getDefault(), "/youtube/v3/commentThreads?key=%s&textFormat=plainText&part=snippet&videoId=%s&order=time&maxResults=100", BuildConfig.KEY, videoId);
        service.getComment(url).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    public void getVideoDetail(String videoId, final int requestCode) {
        String url = String.format(Locale.getDefault(), "/youtube/v3/videos?part=statistics&id=%s&key=%s", videoId, BuildConfig.KEY);
        service.getVideoDetail(url).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }
}
