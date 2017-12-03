package br.com.infoterras.agataterras.network;

import com.google.gson.JsonObject;

import java.util.HashMap;

import br.com.infoterras.agataterras.model.YouTubeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by gustavoterras on 26/03/17.
 */

public interface IConsumerService {

    @GET
    Call<YouTubeResponse> getContent(@Url String url);

    @GET
    Call<JsonObject> getComment(@Url String url);

    @GET
    Call<JsonObject> getVideoDetail(@Url String url);

    @POST
    Call<JsonObject> addComment(@HeaderMap HashMap<String, String> header, @Url String url, @Body JsonObject body);
}
