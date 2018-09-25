package vladyslav.lubenets.redditclient.di.component

import android.content.Context

import javax.inject.Singleton

import dagger.Component
import vladyslav.lubenets.domain.executor.PostExecutionThread
import vladyslav.lubenets.domain.executor.ThreadExecutor
import vladyslav.lubenets.domain.repository.ImageRepository
import vladyslav.lubenets.domain.repository.ItemRepository
import vladyslav.lubenets.redditclient.MainActivity
import vladyslav.lubenets.redditclient.di.module.ApplicationModule
import vladyslav.lubenets.redditclient.di.module.NetworkModule

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: MainActivity)

    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun itemRepository(): ItemRepository
    fun imageRepository(): ImageRepository
}
