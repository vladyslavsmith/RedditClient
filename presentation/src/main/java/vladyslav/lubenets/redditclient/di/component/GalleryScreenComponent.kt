package vladyslav.lubenets.redditclient.di.component

import dagger.Component
import vladyslav.lubenets.redditclient.di.ScreenScope
import vladyslav.lubenets.redditclient.di.module.GalleryScreenModule
import vladyslav.lubenets.redditclient.screen.gallery.GalleryController

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
@ScreenScope(GalleryController::class)
@Component(dependencies = [ApplicationComponent::class], modules = [GalleryScreenModule::class])
interface GalleryScreenComponent {
    fun inject(galleryController: GalleryController)
}