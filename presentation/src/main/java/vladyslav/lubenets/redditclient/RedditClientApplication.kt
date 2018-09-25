package vladyslav.lubenets.redditclient

import android.app.Application
import com.facebook.stetho.Stetho
import vladyslav.lubenets.redditclient.di.ComponentProvider
import vladyslav.lubenets.redditclient.di.component.ApplicationComponent
import vladyslav.lubenets.redditclient.di.component.DaggerApplicationComponent
import vladyslav.lubenets.redditclient.di.module.ApplicationModule


/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class RedditClientApplication : Application(), ComponentProvider<ApplicationComponent> {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        Stetho.initializeWithDefaults(this)
    }

    private fun initializeDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun provideComponent(): ApplicationComponent {
        return applicationComponent
    }

}
