package com.jpiser.hubclient.retrofit;

import com.jpiser.hubclient.common.logging.LogHelper;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitRepository {

    private static final String AUTHORIZATION = "Authorization";

    protected LogHelper logHelper;

    public RetrofitRepository(LogHelper logHelper) {
        this.logHelper = logHelper;
    }

    protected Retrofit createRetrofit(com.jpiser.hubclient.data.models.shared.Credentials credentials, String baseUrl){

        return   new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createHttpClient(credentials))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    protected OkHttpClient createHttpClient(final com.jpiser.hubclient.data.models.shared.Credentials credentials) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        if(credentials != null && !credentials.readOnly()){

            okHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header(AUTHORIZATION, Credentials.basic(credentials.getUsername(), credentials.getPassword()))
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        if(logHelper.debugMode()){
            //Add as last interceptor
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        return okHttpClientBuilder.build();
    }
}
