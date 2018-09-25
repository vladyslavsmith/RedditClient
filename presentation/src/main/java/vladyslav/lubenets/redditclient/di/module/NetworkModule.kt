package vladyslav.lubenets.redditclient.di.module

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import vladyslav.lubenets.data.net.retrofit.RetrofitProviderImpl
import vladyslav.lubenets.data.net.retrofit.api.RedditApi

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(retrofitProviderImpl: RetrofitProviderImpl) : Retrofit {
        return retrofitProviderImpl.provide()
    }

    @Provides
    @Singleton
    internal fun provideRedditApi(retrofit: Retrofit) : RedditApi {
        return retrofit.create(RedditApi::class.java)
    }
}
