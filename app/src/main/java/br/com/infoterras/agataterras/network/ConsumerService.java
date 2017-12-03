package br.com.infoterras.agataterras.network;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Locale;

import br.com.infoterras.agataterras.BuildConfig;
import br.com.infoterras.agataterras.model.YouTubeResponse;
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

    public void addComment(String channelId, String videoId, String comment, final int requestCode) {
        String url = String.format(Locale.getDefault(), "/youtube/v3/commentThreads?key=%s&part=snippet", BuildConfig.KEY);

        JsonObject textOriginal = new JsonObject();
        textOriginal.addProperty("textOriginal", comment);

        JsonObject topLevelComment = new JsonObject();
        topLevelComment.add("topLevelComment", textOriginal);

        JsonObject snippet = new JsonObject();
        snippet.add("topLevelComment", topLevelComment);
        snippet.addProperty("channelId", channelId);
        snippet.addProperty("videoId", videoId);

        JsonObject body = new JsonObject();
        body.add("snippet", snippet);

        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer AIzaSyDDFhP3grrEOodVNIzFDphKhnO6ugmx3_k");

        service.addComment(header, url, body).enqueue(new Callback<JsonObject>() {
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
