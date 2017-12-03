package br.com.infoterras.agataterras.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by gustavoterras on 26/03/17.
 */

public class ServiceGenerator {

    public static <S> S createService(boolean isDebug, String urlBase, Class<S> serviceClass) {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create());

        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));

        Retrofit retrofit = builder.client(httpClient.build()).build();

        return retrofit.create(serviceClass);
    }
}