package br.com.infoterras.youtuberapp.network;

import br.com.infoterras.youtuberapp.model.YouTubeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by gustavoterras on 26/03/17.
 */

public interface IConsumerService {

    @GET()
    Call<YouTubeResponse> getContent(@Url String url);

    @GET()
    Call<YouTubeResponse> getComment(@Url String url);

}
