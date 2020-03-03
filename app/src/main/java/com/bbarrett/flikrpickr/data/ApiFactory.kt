package com.bbarrett.flikrpickr.data

import com.bbarrett.flikrpickr.BuildConfig
import com.bbarrett.flikrpickr.data.remote.api.FlickrApi
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author bbarrett on 2020-02-25
 */

object ApiFactory {

    private val requestInterceptor = Interceptor { chain ->
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.FLIKR_API_KEY)
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        chain.proceed(request)
    }

    fun getDebugInterceptor(): HttpLoggingInterceptor {
        val debugInterceptor = HttpLoggingInterceptor()
        debugInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return debugInterceptor
    }

    private val mFlickrClient = OkHttpClient().newBuilder()
        .addInterceptor(requestInterceptor)
        .addNetworkInterceptor(getDebugInterceptor())
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(mFlickrClient)
        .baseUrl("https://api.flickr.com/services/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val flickrApi: FlickrApi = retrofit().create(FlickrApi::class.java)
}