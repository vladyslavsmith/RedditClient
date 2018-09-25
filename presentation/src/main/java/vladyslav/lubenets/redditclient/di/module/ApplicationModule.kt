package vladyslav.lubenets.redditclient.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import vladyslav.lubenets.data.executor.BackgroundExecutor
import vladyslav.lubenets.data.repository.ImageRepositoryImpl
import vladyslav.lubenets.data.repository.ItemRepositoryImpl
import vladyslav.lubenets.domain.AppInfoProvider
import vladyslav.lubenets.domain.executor.PostExecutionThread
import vladyslav.lubenets.domain.executor.ThreadExecutor
import vladyslav.lubenets.domain.repository.ImageRepository
import vladyslav.lubenets.domain.repository.ItemRepository
import vladyslav.lubenets.redditclient.AppInfoProviderImpl
import vladyslav.lubenets.redditclient.MainThread
import vladyslav.lubenets.redditclient.RedditClientApplication
import javax.inject.Singleton

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@Module
class ApplicationModule(private val application: RedditClientApplication) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(backgroundExecutor: BackgroundExecutor): ThreadExecutor {
        return backgroundExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: MainThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    internal fun provideItemRepository(itemRepository: ItemRepositoryImpl): ItemRepository {
        return itemRepository
    }

    @Provides
    @Singleton
    internal fun provideImageRepository(imageRepository: ImageRepositoryImpl): ImageRepository {
        return imageRepository
    }

    @Provides
    @Singleton
    internal fun provideAppInfo(appInfoProvider: AppInfoProviderImpl): AppInfoProvider {
        return appInfoProvider
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

}