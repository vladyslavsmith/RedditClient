package vladyslav.lubenets.redditclient.di.component

import dagger.Component
import vladyslav.lubenets.redditclient.di.ScreenScope
import vladyslav.lubenets.redditclient.di.module.TopScreenModule
import vladyslav.lubenets.redditclient.screen.top.TopController

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@ScreenScope(TopController::class)
@Component(dependencies = [ApplicationComponent::class], modules = [TopScreenModule::class])
interface TopScreenComponent {
    fun inject(topController: TopController)
}