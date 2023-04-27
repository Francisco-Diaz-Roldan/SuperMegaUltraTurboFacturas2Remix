package com.example.supermegaultraturbofacturas2remix.io.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Creamos un adapter para coger los datos de intenert
public class APIAdapter {

    private static FacturasService API_SERVICE;

    private static final String BASE_URL = "https://viewnextandroid.wiremockapi.cloud/";

    public static FacturasService getApiService() {

        // Creao un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asocio el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- set log level
                    .build();
            API_SERVICE = retrofit.create(FacturasService.class);
        }
        return API_SERVICE;
    }

}

