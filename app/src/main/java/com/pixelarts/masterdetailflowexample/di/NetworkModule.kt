package com.pixelarts.masterdetailflowexample.di

import com.pixelarts.masterdetailflowexample.common.*
import com.pixelarts.masterdetailflowexample.net.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesIntercptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(interceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit):NetworkService = retrofit.create(NetworkService::class.java)
}