package com.rajkumarrajan.mvvm_architecture.data.api

import androidx.annotation.NonNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitInstance {

    private val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
    val BASE_URL_List = "https://5e510330f2c0d300147c034c.mockapi.io/"

    //public static final String API_BASE_URL = "http://keantrolley.bbapi.co/stations/";
    private val httpClient = OkHttpClient()
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val builder1 = Retrofit.Builder()
        .baseUrl(BASE_URL_List)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient).build()
        return retrofit.create(serviceClass)
    }

    fun <S> createService1(serviceClass: Class<S>): S {
        val retrofit = builder1.client(httpClient).build()
        return retrofit.create(serviceClass)
    }


    fun <S> createServiceHeader(
        serviceClass: Class<S>, packageName: String,
        SHA1: String
    ): S {

        val okClient = OkHttpClient.Builder()
            .addInterceptor(
                object : Interceptor {
                    @NonNull
                    @Throws(IOException::class)
                    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
                        val original = chain.request()

                        // Request customization: add request headers
                        val requestBuilder = original.newBuilder()
                            .header("X-Android-Package", packageName)
                            .header("X-Android-Cert", SHA1)
                            .method(original.method(), original.body())

                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                })
            .build()

        val retrofit = builder.client(okClient).build()
        return retrofit.create(serviceClass)
    }
}
